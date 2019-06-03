package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.dto.UserDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private ClientRepo clientRepo;


    //Возвращаем строку успешной регистрации, либо ошибки, которую контроллер отправляет в форму.
    public String doRegister(UserDto user, ClientDto client) {
        User userFromDB = userRepo.findByUsername(user.getUsername());
        Client clientFromDB = clientRepo.findClientByPassportNumber(client.getPassportNumber());


        StringBuilder sb = new StringBuilder();
        if(userFromDB != null) {
            sb.append("Такой пользователь уже существует.\n");
        }

        if(clientFromDB != null) {
            sb.append("Клиент с таким номером паспорта уже существует.");
        }

        if(clientFromDB == null && userFromDB == null) {
            Client clientEntity = new Client(client.getName(), client.getMidName(),
                    client.getLastName(), client.getPassportNumber());
            clientRepo.save(clientEntity);

            User userEntity = new User(user.getUsername(),user.getPassword(), true);
            userEntity.setClient(clientEntity);
            userRepo.save(userEntity);
        }

        return sb.toString();
    }
}
