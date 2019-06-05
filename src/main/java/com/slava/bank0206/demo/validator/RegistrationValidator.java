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

    public RegisterValid validateRegistrationForm(UserDto user, ClientDto client) {

        RegisterValid registerValid = new RegisterValid();

        if(userRepo.findByUsername(user.getUsername()) != null) {
            registerValid.setUniqUserName(false);
        }

        if(clientRepo.findClientByPassportNumber(client.getPassportNumber()) != null) {
            registerValid.setUniqPassport(false);
        }

        if(user.getUsername().isEmpty()) {
            registerValid.setValidUsername(false);
        }

        if(user.getPassword().isEmpty()) {
            registerValid.setValidPassport(false);
        }

        if(client.getName().isEmpty()) {
            registerValid.setValidName(false);
        }

        if(client.getName().isEmpty()) {
            registerValid.setValidMidName(false);
        }

        if(client.getName().isEmpty()) {
            registerValid.setValidLastName(false);
        }

        if(client.getName().isEmpty()) {
            registerValid.setValidPassport(false);
        }

        return registerValid;
    }
}
