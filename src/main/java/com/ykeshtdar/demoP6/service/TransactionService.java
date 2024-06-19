package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public List<Transaction> displayTransaction(int receiverId, int senderId){
       return transactionRepository.findTransactions(receiverId,senderId);
    }
}
