package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.Role;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/registration")
    public String showRegistration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(UserDto user, ClientDto client, Map<String, Object> model) {

        String message = registrationService.doRegister(user,client);

        if(message.length() > 0) {
            model.put("message", message);
            return "registration";
        }

        model.put("message", "Регистрация прошла успешно.");
        return "redirect:/login";
    }


}
