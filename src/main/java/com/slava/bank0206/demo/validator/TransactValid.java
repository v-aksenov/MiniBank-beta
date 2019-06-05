package com.slava.bank0206.demo.validator;

public class TransactValid {

    private boolean valid;
    private boolean validUserFrom;
    private boolean validUserTo;
    private boolean validAmount;
    private boolean validBalance;

    public TransactValid() {
        this.valid = true;
        this.validUserFrom = true;
        this.validUserTo = true;
        this.validAmount = true;
        this.validBalance = true;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValidUserFrom() {
        return validUserFrom;
    }

    public void setValidUserFrom(boolean validUserFrom) {
        this.valid = false;
        this.validUserFrom = validUserFrom;
    }

    public boolean isValidUserTo() {
        return validUserTo;
    }

    public void setValidUserTo(boolean validUserTo) {
        this.valid = false;
        this.validUserTo = validUserTo;
    }

    public boolean isValidAmount() {
        return validAmount;
    }

    public void setValidAmount(boolean validAmount) {
        this.valid = false;
        this.validAmount = validAmount;
    }

    public boolean isValidBalance() {
        return validBalance;
    }

    public void setValidBalance(boolean validBalance) {
        this.valid = false;
        this.validBalance = validBalance;
    }
}
