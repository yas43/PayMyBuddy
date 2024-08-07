package com.ykeshtdar.demoP6.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;
    @Column(name = "username")
    String username;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @Column(name = "role" )
    String role ;
    @ManyToMany
    @JoinTable(
            name = "friend",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    Set<User>userSet = new HashSet<>();

    @OneToMany(mappedBy = "sender")
    Set<Transaction>sendertransactionSet = new HashSet<>();
    @OneToMany(mappedBy = "receiver")
    Set<Transaction> recievertransactionSet = new HashSet<>();

    @Column(name = "balance")
    float balance;


}
