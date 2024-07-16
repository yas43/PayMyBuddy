package com.ykeshtdar.demoP6.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.*;
import java.util.*;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @ManyToOne
    @JoinColumn(name = "sender_id")
    User sender;
    @ManyToOne
    @JoinColumn(name = "receiver_id")
    User receiver;
    @Column(name = "description")
    String description;
    @Column(name = "amount")
    float amount;
    @Column(name = "fee")
    float fee;
    @Column(name = "date")
    Date date;

}
