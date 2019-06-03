package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.service.TransactService;
import com.slava.bank0206.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Map;

@Controller
public class TransactController {

    @Autowired
    private TransactService transactService;

    @Autowired
    private UserService userService;

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
            Long amount,
            Map<String, Object> model
    ) {
        transactService.deposit(user,amount);
        model.put("message", "Пополнение прошло успешно!");
        return "success";
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
        if (username.isEmpty()) {
            model.put("message", "Введите логин получателя!");
            return "transfer";
        }

        User toUser = userService.getUserByUsername(username);

        if(toUser == null) {
            model.put("message", "Получателя не существует!");
            return "error";
        }

        try {
            
            Long amount = Long.valueOf(amountString);

            if(amount < 0) {
                model.put("message", "Введите корректкную сумму для перевода.");
                return "transfer";
            }

            if(!transactService.transfer(user, toUser, amount)) {
                    model.put("message","На счете недостаточно средств для совершения перевода.");
                    return "error";
            }

            model.put("message", "Перевод прошел успешно!");
            return "success";

        } catch (NumberFormatException e) {
            model.put("message", "Введите сумму для перевода в виде целого неотрицательного числа.");
            return "transfer";
        }
    }
}
