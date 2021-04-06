package com.example.bankingsystemapp.Data;

public class transaction {
    private int ID;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    private String sender;
    private String receiver;
    private int amount;
    private int status;
    private String Date;

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public transaction(String sender, String receiver, int status, int amount,String Date) {
        this.sender = sender;
        this.receiver = receiver;
        this.status = status;
        this.amount = amount;
        this.Date = Date;
    }

    public transaction() {
    }
}
