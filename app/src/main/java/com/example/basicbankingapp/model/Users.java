package com.example.basicbankingapp.model;

public class Users {
    String name, mob_no, amount, accountno, ifsc, email;

    public Users(String name, String mob_no, String amount, String accountno, String ifsc, String email) {
        this.name = name;
        this.mob_no = mob_no;
        this.amount = amount;
        this.accountno = accountno;
        this.ifsc = ifsc;
        this.email = email;
    }

    public String getAccountno() {
        return accountno;
    }

    public void setAccountno(String accountno) {
        this.accountno = accountno;
    }

    public String getIfsc() {
        return ifsc;
    }

    public void setIfsc(String ifsc) {
        this.ifsc = ifsc;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
