package com.buer.bankrupt.model;

public record User (
    String name,
    String account,
    String pin,
    Double saldo
) {}
