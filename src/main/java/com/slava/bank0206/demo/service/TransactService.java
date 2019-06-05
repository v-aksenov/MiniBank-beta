package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.TransactionType;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.Transaction;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.TransactRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.validator.TransactValid;
import com.slava.bank0206.demo.validator.TransactionValidator;
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

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TransactionValidator transactionValidator;

    public TransactValid deposit(User user, String amount) {

        TransactValid transactValid = transactionValidator.validateDeposit(amount);
        if(transactValid.isValid()) {

            Long amountLong = Long.valueOf(amount);
            Transaction transaction = new Transaction(null,user, amountLong);
            Long balance = user.getClient().getBalance();
            balance += amountLong;
            user.getClient().setBalance(balance);

            clientRepo.save(user.getClient());
            transactRepo.save(transaction);
        }

        return transactValid;
    }

    public TransactValid transfer(User fromUser, String toUser, String amount) {

        TransactValid transactValid = new TransactValid();

        //Проверка на существование получателя. Проводится здесь, чтобы не делать дублирующий запрос в базу.
        User userTo = userRepo.findByUsername(toUser);
        if(userTo == null) {
            transactValid.setValidUserTo(false);
        }

        //Проверка остальных факторов
        transactionValidator.validateTransaction(fromUser,amount,transactValid);

        if(transactValid.isValid()) {

            Long amountLong = Long.valueOf(amount);
            Transaction transaction = new Transaction(fromUser,userTo,amountLong);

            Client clientFrom = fromUser.getClient();
            Long balanceUserFrom = clientFrom.getBalance();
            balanceUserFrom -= amountLong;
            clientFrom.setBalance(balanceUserFrom);

            Client clientTo = userTo.getClient();
            Long balanceToUser = clientTo.getBalance();
            balanceToUser += amountLong;
            clientTo.setBalance(balanceToUser);

            clientRepo.save(clientFrom);
            clientRepo.flush();
            clientRepo.save(clientTo);
            transactRepo.save(transaction);
        }

        return transactValid;
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
