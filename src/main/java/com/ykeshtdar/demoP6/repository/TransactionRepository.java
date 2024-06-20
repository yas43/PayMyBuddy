package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {
//    @Query("SELECT DISTINCT b.receiver.id, t.description, t.amount " +
//            "FROM Transaction t " +
//            "JOIN Beneficiary b " +
//            "ON b.receiver.id = :receiverId " +
//            "AND t.sender.id = :senderId")
//    List<Transaction> findTransactions(@Param("receiverId") Integer receiverId,
//                                            @Param("senderId") Integer senderId);
}
