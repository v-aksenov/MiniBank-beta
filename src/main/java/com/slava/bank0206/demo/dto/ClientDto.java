package com.slava.bank0206.demo.dto;

public class ClientDto {

    private String name;

    private String midName;

    private String lastName;

    private String passportNumber;

    public ClientDto(String name, String midName, String lastName, String passportNumber) {
        this.name = name;
        this.midName = midName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassportNumber() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber = passportNumber;
    }

    @Override
    public String toString() {
        return  "name='" + name + '\'' +
                ", midName='" + midName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber='" + passportNumber + '\'' +
                '}';
    }
}
