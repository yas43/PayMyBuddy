package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Integer> {

    @Query("select new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id ,t.receiver.username, t.description, t.amount)" +
            "            FROM Transaction t  " +
            "            WHERE (t.sender.id= :sender_id " +
            "            AND t.receiver.id= :receiver_id )")
    List<TransactionHistory> findTransaction(@Param("sender_id")int senderId,@Param("receiver_id")int receiverId);




    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id ,r.username, t.description, t.amount)" +
            "            FROM Transaction t  " +
            "            JOIN t.receiver r   " +
            "            WHERE t.sender.id= :senderId ")
    List<TransactionHistory> findAllTransaction(@Param("senderId")int senderId);

}
