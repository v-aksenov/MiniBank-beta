package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private RegistrationValidator registrationValidator;

    //Возвращаем строку успешной регистрации, либо ошибки, которую контроллер отправляет в форму.
    public String doRegister(UserDto user, ClientDto client) {

        StringBuilder sb = new StringBuilder();
        Map<String,String> validation = registrationValidator.validateRegistrationForm(user,client);
        if(validation.isEmpty()) {
            Client clientEntity = new Client(client.getName(), client.getMidName(),
                    client.getLastName(), client.getPassportNumber());
            clientRepo.save(clientEntity);

            User userEntity = new User(user.getUsername(),user.getPassword(), true);
            userEntity.setClient(clientEntity);
            userRepo.save(userEntity);
        } else {
            for (String s: validation.keySet()) {
                sb.append(validation.get(s));
            }
        }

        return sb.toString();
    }
}
