package com.ykeshtdar.demoP6.model.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionHistory {

    int id;


    String receiver;

    String description;

    float amount;



}
