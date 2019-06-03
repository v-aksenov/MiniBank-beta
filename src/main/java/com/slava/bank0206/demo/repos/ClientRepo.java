package com.slava.bank0206.demo.repos;

import com.slava.bank0206.demo.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client,Long> {
    Client findClientByPassportNumber(String passportNumber);
}
