package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.service.ClientService;
import com.slava.bank0206.demo.service.TransactService;
import com.slava.bank0206.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactService transactService;

    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String mainPage(
            @RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Map<String, Object> model
    ) {
        model.put("name", name);
        return "index";
    }

    @GetMapping("/allClients")
    public String allClient(Map<String, Object> model) {

        model.put("users", userService.getAll());
        return "allClients";
    }

    @GetMapping("/home")
    public String showHome(
            @AuthenticationPrincipal User user,
            Map<String, Object> model) {
        model.put("client", clientService.getClientByUsername(user.getUsername()));
        model.put("transactions", transactService.getAll(user));

        return "home";
    }

}
