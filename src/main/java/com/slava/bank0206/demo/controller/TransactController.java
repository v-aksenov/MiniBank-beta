package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.service.TransactService;
import com.slava.bank0206.demo.service.UserService;
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
            Long amount,
            Map<String, Object> model
    ) {
        User toUser = userService.getUserByUsername(username);

        if(toUser == null) {
            model.put("message", "Получателя не существует!");
            return "error";
        }

        transactService.transfer(user, toUser, amount);

        model.put("message", "Перевод прошел успешно!");
        return "success";
    }
}
