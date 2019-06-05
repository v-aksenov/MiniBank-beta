package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import com.slava.bank0206.demo.validator.RegisterValid;
import com.slava.bank0206.demo.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private RegistrationValidator registrationValidator;


    public RegisterValid doRegister(UserDto user, ClientDto client) {

        RegisterValid registerValid = registrationValidator.validateRegistrationForm(user,client);

        if(registerValid.isValid()) {
            Client clientEntity = new Client(client.getName(),client.getMidName(),client.getLastName(),client.getPassportNumber());
            User userEntity = new User(user.getUsername(),user.getPassword(),true);
            userEntity.setClient(clientEntity);
            clientRepo.save(clientEntity);
            userRepo.save(userEntity);
        }

        return registerValid;
    }
}
