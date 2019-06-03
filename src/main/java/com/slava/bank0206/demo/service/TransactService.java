package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.TransactionType;
import com.slava.bank0206.demo.entity.Transaction;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.TransactRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public void transfer(User fromUser, User toUser, Long amount) {
        Transaction transaction = new Transaction(fromUser,toUser,amount);
        Long balanceUserFrom = fromUser.getClient().getBalance();
        balanceUserFrom -= amount;
        fromUser.getClient().setBalance(balanceUserFrom);

        Long balanceToUser = toUser.getClient().getBalance();
        balanceToUser += amount;
        toUser.getClient().setBalance(balanceToUser);

        clientRepo.save(fromUser.getClient());
        clientRepo.save(toUser.getClient());
        transactRepo.save(transaction);
    }

    public List<TransactionType> getAll(User user) {
        List<Transaction> transactions = transactRepo.findAllByToUserOrFromUser(user,user);

        List<TransactionType> transactionTypes = new ArrayList<>();
        for (Transaction t: transactions) {
            transactionTypes.add(new TransactionType(t.getFromUser(),t.getToUser(),t.getAmount(),user));
        }
        return transactionTypes;
    }
}
