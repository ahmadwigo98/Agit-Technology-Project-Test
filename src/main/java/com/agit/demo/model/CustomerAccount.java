package com.agit.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "account_number")
    private String accountNumber;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "balance")
    private Integer balance;

    public CustomerAccount(String accountNumber, String customerName, Integer balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.balance = balance;
    }

    public void transfer(CustomerAccount otherAccount, int amount) {
        this.balance -= amount;
        otherAccount.balance += amount;
    }
}