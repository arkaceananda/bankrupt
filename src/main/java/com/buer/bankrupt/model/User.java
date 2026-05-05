package com.buer.bankrupt.model;

public class User {
    String name;
    String account;
    String pin;

    Double saldo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public User(String name, String account, String pin, Double saldo) {
        this.name = name;
        this.pin = pin;
        this.account = account;
        this.saldo = saldo;
    }
}
