package com.ykeshtdar.demoP6.model.dto;

import jakarta.persistence.*;

@Entity
public class TransactionHistory {
    String receiver;
    String description;
    String amount;

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
