package com.slava.bank0206.demo.repos;

import com.slava.bank0206.demo.entity.Transaction;
import com.slava.bank0206.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findAllByToUserOrFromUser(User fromUser, User toUser);
}
