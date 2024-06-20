package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
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


//    SELECT user.username,description,amount FROM `transaction` JOIN user ON sender_id=1 and receiver_id=3 WHERE receiver_id=user.id;



    @Query(" SELECT t.sender.username, t.description, t.amount\n" +
            "FROM Transaction t " +
            "JOIN t.sender senderUser " +
            "JOIN t.receiver receiverUser " +
            "WHERE senderUser.id = :senderId AND receiverUser.id = :receiverId AND t.receiver.id = receiverUser.id ")
    List<TransactionHistory> findTransactions(@Param("senderId") Integer senderId,
                                              @Param("receiverId") Integer receiverId);
}
