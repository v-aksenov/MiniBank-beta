package com.slava.bank0206.demo.dto;


import com.slava.bank0206.demo.entity.User;

public class TransactionType {
    private User fromUser;
    private User toUser;
    private Long amount;
    private String type;

    public TransactionType() {
    }

    public User getFromUser() {
        return fromUser;
    }

    public String getFromUsername() {
        return fromUser != null ? fromUser.getUsername() : "Банк";
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public TransactionType(User fromUser, User toUser, Long amount, User baseUser) {
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.amount = amount;
        try {
            String fromUsername = fromUser.getUsername();
            String toUsername = toUser.getUsername();
            String baseUsername = baseUser.getUsername();
            if(fromUsername.equals(baseUsername)) {
                this.type = "Перевод";
            }
            if(toUsername.equals(baseUsername)) {
                this.type = "Пополнение";
            }
        }catch (NullPointerException e) {
            this.type = "Пополнение";
        }
    }
}
