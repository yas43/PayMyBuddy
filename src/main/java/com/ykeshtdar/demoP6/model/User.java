package com.ykeshtdar.demoP6.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "user")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;

    @OneToMany(mappedBy = "sender")
    List<Transaction> transactionList;
    @Column(name = "balance")
    float balance;

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public float getBalance() {
        return balance;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }
}
