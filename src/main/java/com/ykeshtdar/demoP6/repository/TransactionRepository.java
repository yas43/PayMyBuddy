package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.http.converter.json.*;
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


//    SELECT user.username,description,amount FROM `transaction` JOIN user ON sender_id=1 and receiver_id=3 WHERE receiver_id=user.id;




    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.sender.username, t.description, t.amount) " +
            "FROM Transaction t " +
            "WHERE t.sender.id = :senderId AND t.receiver.id = :receiverId")
    List<TransactionHistory> findTransactions(@Param("senderId") int senderId, @Param("receiverId") int receiverId);
}
