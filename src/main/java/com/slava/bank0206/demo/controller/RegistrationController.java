package com.slava.bank0206.demo.controller;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.service.RegistrationService;
import com.slava.bank0206.demo.validator.RegisterValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

        RegisterValid registerValid = registrationService.doRegister(user,client);

        if(registerValid.isValid()) {

            model.put("success", "Регистрация прошла успешно!");
            return "registration";

        } else {

            if(!registerValid.isUniqUserName()) {
                model.put("message", "Клиент с таким логином уже существует!");
                return "registration";
            }

            if(!registerValid.isUniqPassport()) {
                model.put("message", "Клиент с таким номером паспорта уже существует!");
                return "registration";
            }


        }
        model.put("message", "Неизвестная ошибка!");
        return "registration";
    }

    @GetMapping("/login")
    public String showLogin(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Map<String, Object> model) {

        if (error != null) {
            model.put("error", "Неверный логин или пароль!");
        }

        if (logout != null) {
            model.put("message", "Вы успешно вышли из учетной записи.");
        }

        return "login";
    }


}
