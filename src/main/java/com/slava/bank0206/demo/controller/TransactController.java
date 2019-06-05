package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.service.TransactService;
import com.slava.bank0206.demo.validator.TransactValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class TransactController {

    @Autowired
    private TransactService transactService;

    @GetMapping("/deposit")
    public String deposit(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        model.put("client",user.getClient());
        return "deposit";
    }

    @PostMapping("/deposit")
    public String doDeposit(
            @AuthenticationPrincipal User user,
            String amount,
            Map<String, Object> model
    ) {
        TransactValid transactValid = transactService.deposit(user,amount);

        if(transactValid.isValid()) {
            model.put("message", "Пополнение прошло успешно!");
            return "success";
        }

        model.put("message", "Введите сумму в виде целого неотрицательного числа.");
        return "deposit";
    }

    @GetMapping("/transfer")
    public String transfer(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        model.put("client", user.getClient());
        return "transfer";
    }

    @PostMapping("/transfer")
    public String doTransfer(
            @AuthenticationPrincipal User user,
            String username,
            String amountString,
            Map<String, Object> model
    ) {

        TransactValid transactValid = transactService.transfer(user,username,amountString);

        if(transactValid.isValid()) {
            model.put("message", "Перевод прошел успешно!");
            return "success";
        }

        if(!transactValid.isValidAmount()) {
            model.put("message", "Введите сумму для перевода в виде целого неотрицательного числа.");
            return "transfer";
        }

        if(!transactValid.isValidBalance()) {
            model.put("message","На счете недостаточно средств для совершения перевода.");
            return "error";
        }

        if(!transactValid.isValidUserTo()) {
            model.put("message", "Получателя не существует!");
            return "error";
        }

        if (username.isEmpty()) {
            model.put("message", "Введите логин получателя!");
            return "transfer";
        }

        model.put("message", "Неизвестная ошибка");
        return "error";
    }
}
