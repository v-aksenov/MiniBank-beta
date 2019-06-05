package com.slava.bank0206.demo.validator;

public class RegisterValid {

    private boolean valid;
    private boolean validName;
    private boolean validMidName;
    private boolean validLastName;
    private boolean validPassport;
    private boolean uniqPassport;



    private boolean validUsername;
    private boolean uniqUserName;
    private boolean validPassword;

    public RegisterValid() {
        this.valid = true;
        this.validName = true;
        this.validMidName = true;
        this.validLastName = true;
        this.validPassport = true;
        this.uniqPassport = true;
        this.validUsername = true;
        this.uniqUserName = true;
        this.validPassword = true;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isUniqPassport() {
        return uniqPassport;
    }

    public void setUniqPassport(boolean uniqPassport) {
        this.valid = false;
        this.uniqPassport = uniqPassport;
    }

    public boolean isUniqUserName() {
        return uniqUserName;
    }

    public void setUniqUserName(boolean uniqUserName) {
        this.valid = false;
        this.uniqUserName = uniqUserName;
    }

    public boolean isValidName() {
        return validName;
    }

    public void setValidName(boolean validName) {
        this.valid = false;
        this.validName = validName;
    }

    public boolean isValidMidName() {
        return validMidName;
    }

    public void setValidMidName(boolean validMidName) {
        this.valid = false;
        this.validMidName = validMidName;
    }

    public boolean isValidLastName() {
        return validLastName;
    }

    public void setValidLastName(boolean validLastName) {
        this.valid = false;
        this.validLastName = validLastName;
    }

    public boolean isValidPassport() {
        return validPassport;
    }

    public void setValidPassport(boolean validPassport) {
        this.valid = false;
        this.validPassport = validPassport;
    }

    public boolean isValidUsername() {
        return validUsername;
    }

    public void setValidUsername(boolean validUsername) {
        this.valid = false;
        this.validUsername = validUsername;
    }

    public boolean isValidPassword() {
        return validPassword;
    }

    public void setValidPassword(boolean validPassword) {
        this.valid = false;
        this.validPassword = validPassword;
    }
}
