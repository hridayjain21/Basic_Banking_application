package com.example.bankingsystemapp.Data;

import java.io.Serializable;

public class user implements Serializable {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String name;
    private String email;
    private int accountNumber;
    private int balance;
    private String phoneNumber;
    private String ifscCode;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public user() {
    }

    public user(String name, String email, int accountNumber, int balance, String phoneNumber, String ifscCode) {
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.phoneNumber = phoneNumber;
        this.ifscCode = ifscCode;
    }
}
