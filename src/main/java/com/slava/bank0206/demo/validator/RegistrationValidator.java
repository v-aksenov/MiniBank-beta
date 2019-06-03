package com.slava.bank0206.demo.validator;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RegistrationValidator {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;

    public Map<String,String> validateRegistrationForm(UserDto user, ClientDto client) {
        Map<String,String> validateMap = new HashMap<>();
//        validateMap.put("username","");
//        validateMap.put("password","");
//        validateMap.put("name","");
//        validateMap.put("midName","");
//        validateMap.put("lastName","");
//        validateMap.put("passportNumber","");

        if(user.getUsername().isEmpty()) {
            validateMap.put("username","Логин не может быть пустым!\n");
        }

        if(user.getPassword().isEmpty()) {
            validateMap.put("password","Пароль не может быть пустым!\n");
        }

        if(client.getName().isEmpty()) {
            validateMap.put("name","Имя не может быть пустым!\n");
        }

        if(client.getName().isEmpty()) {
            validateMap.put("midName","Отчество не может быть пустым!\n");
        }

        if(client.getName().isEmpty()) {
            validateMap.put("lastName","Фамилия не может быть пустым!\n");
        }

        if(client.getName().isEmpty()) {
            validateMap.put("passportNumber","Паспорт не может быть пустым!\n");
        }

        User userFromDB = userRepo.findByUsername(user.getUsername());
        Client clientFromDB = clientRepo.findClientByPassportNumber(client.getPassportNumber());


        StringBuilder sb = new StringBuilder();
        if(userFromDB != null) {
            sb.append("Такой пользователь уже существует.\n");
        }

        if(clientFromDB != null) {
            sb.append("Клиент с таким номером паспорта уже существует.");
        }

        return validateMap;
    }
}
