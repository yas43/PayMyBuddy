package com.ykeshtdar.demoP6.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "beneficiary")
@Getter
@Setter
public class Beneficiary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @OneToMany(mappedBy = "receiver")
    List<Transaction> transactionlist;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "amount")
    float amount;



}
