package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.TransactionType;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.Transaction;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.TransactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactService {

    @Autowired
    private TransactRepo transactRepo;

    @Autowired
    private ClientRepo clientRepo;

    public void deposit(User user, Long amount) {
        Transaction transaction = new Transaction(null,user, amount);
        Long balance = user.getClient().getBalance();
        balance += amount;
        user.getClient().setBalance(balance);
        clientRepo.save(user.getClient());
        transactRepo.save(transaction);
    }

    public boolean transfer(User fromUser, User toUser, Long amount) {
        Transaction transaction = new Transaction(fromUser,toUser,amount);

        Client clientFrom = fromUser.getClient();
        Long balanceUserFrom = clientFrom.getBalance();

        //Проверка на необходимое количество денег на счете.
        if(balanceUserFrom < amount) {
            return false;
        }

        balanceUserFrom -= amount;
        clientFrom.setBalance(balanceUserFrom);


        Client clientTo = toUser.getClient();
        Long balanceToUser = clientTo.getBalance();
        balanceToUser += amount;
        clientTo.setBalance(balanceToUser);

        clientRepo.save(clientFrom);
        clientRepo.flush();
        clientRepo.save(clientTo);
        transactRepo.save(transaction);

        return true;
    }

    public List<TransactionType> getAll(User user) {
        List<Transaction> transactions = transactRepo.findAllByToUserOrFromUser(user,user);

        List<TransactionType> transactionTypes = new ArrayList<>();
        for (Transaction t: transactions) {
            transactionTypes.add(new TransactionType(t.getFromUser(),t.getToUser(),t.getAmount(), t.getDate(), user));
        }
        Collections.reverse(transactionTypes);
        return transactionTypes;
    }
}
