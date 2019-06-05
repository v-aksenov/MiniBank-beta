package com.slava.bank0206.demo.validator;

import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidator {

    @Autowired
    private UserRepo userRepo;

    public TransactValid validateDeposit(String amount) {

        TransactValid transactValid = new TransactValid();

        try {
            Long amountLong = Long.valueOf(amount);
            if (amountLong < 0) {
                transactValid.setValidAmount(false);
            }
        } catch (NumberFormatException e) {
            transactValid.setValidAmount(false);
        }

        return transactValid;

    }
    public TransactValid validateTransaction(User userFrom, String amount, TransactValid transactValid) {

        //Проверка на правильность введенной суммы
        try {
            Long amountLong = Long.valueOf(amount);
            if (amountLong < 0) {
                transactValid.setValidAmount(false);
            }

            //Проверка на наличие суммы перевода на счете
            if (userFrom.getClient().getBalance() < amountLong) {
                transactValid.setValidBalance(false);
            }

        } catch (NumberFormatException e) {
            transactValid.setValidAmount(false);
        }

        return transactValid;
    }
}
