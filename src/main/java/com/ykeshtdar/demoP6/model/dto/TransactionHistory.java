package com.ykeshtdar.demoP6.model.dto;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.stereotype.*;


@Data
public class TransactionHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String receiver;

    String description;

    String amount;

    public TransactionHistory(int id, String receiver, String description, String amount) {
        this.id = id;
        this.receiver = receiver;
        this.description = description;
        this.amount = amount;
    }

    public TransactionHistory() {
    }

    public String getReceiver() {
        return receiver;
    }

    public String getDescription() {
        return description;
    }

    public String getAmount() {
        return amount;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "TransactionHistory{" +
                "receiver='" + receiver + '\'' +
                ", description='" + description + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
