package com.example.basicbankingapp.model;

public class Transaction_History_Model {
    String from_name, to_name, amount, status, date_time;

    public Transaction_History_Model(String from_name, String to_name, String amount, String status, String date_time) {
        this.from_name = from_name;
        this.to_name = to_name;
        this.amount = amount;
        this.status = status;
        this.date_time = date_time;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getFrom_name() {
        return from_name;
    }

    public void setFrom_name(String from_name) {
        this.from_name = from_name;
    }

    public String getTo_name() {
        return to_name;
    }

    public void setTo_name(String to_name) {
        this.to_name = to_name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
