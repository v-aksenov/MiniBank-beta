package com.slava.bank0206.demo.service;

import com.slava.bank0206.demo.dto.ClientDto;
import com.slava.bank0206.demo.entity.Client;
import com.slava.bank0206.demo.entity.User;
import com.slava.bank0206.demo.repos.ClientRepo;
import com.slava.bank0206.demo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepo clientRepo;

    @Autowired
    private UserRepo userRepo;

    public List<ClientDto> getAll() {
        List<ClientDto> clients = new ArrayList<>();

        Iterable<Client> clients1 = clientRepo.findAll();

        for (Client c : clients1) {
            clients.add(new ClientDto(c.getName(),c.getMidName(),c.getLastName(),c.getPassportNumber()));
        }
        return clients;
    }

    public Client getClientByUsername(String username) {
        User user = userRepo.findByUsername(username);
        return user.getClient();
    }
}
