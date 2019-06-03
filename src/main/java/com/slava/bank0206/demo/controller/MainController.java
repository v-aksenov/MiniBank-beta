package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.service.ClientService;
import com.slava.bank0206.demo.service.TransactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private TransactService transactService;


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
        List<ClientDto> clients = clientService.getAll();
        model.put("clients", clients);
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
