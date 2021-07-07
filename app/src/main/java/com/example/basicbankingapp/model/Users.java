package com.example.basicbankingapp.model;

public class Users {
String name,mob_no,amount;

    public Users(String name, String mob_no, String amount) {
        this.name = name;
        this.mob_no = mob_no;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMob_no() {
        return mob_no;
    }

    public void setMob_no(String mob_no) {
        this.mob_no = mob_no;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
