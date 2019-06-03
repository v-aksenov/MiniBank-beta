package com.slava.bank0206.demo.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp date;

    @ManyToOne
    @JoinColumn
    private User fromUser;
    @ManyToOne
    @JoinColumn
    private User toUser;
    private Long amount;

    public Transaction() {
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public Transaction(User fromUser, User toUser, Long amount) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        this.date = new Timestamp(System.currentTimeMillis());
    }
}
