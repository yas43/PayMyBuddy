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


//  SELECT sender_id,transaction.receiver_id,transaction.description,
//  transaction.amount  FROM transaction JOIN user
//on transaction.sender_id=user.id AND transaction.sender_id=2;

//SELECT DISTINCT transaction.sender_id,transaction.receiver_id,
// transaction.description,transaction.amount FROM transaction
// JOIN user on transaction.receiver_id=user.id AND transaction.sender_id=1;

// it works:
//    @Query(value = "SELECT t.sender_id,t.receiver_id, t.description, t.amount " +
//            "FROM transaction t " +
//            "JOIN user u ON t.receiver_id = u.id " +
//            "WHERE u.username = :username", nativeQuery = true)
//    List<TransactionHistory> findTransactionsByUsername(@Param("username") String username);



//@Query("select t.receiver_id,t.description,t.amount, from transaction t join t.receiver where t.sender_id =:sender_id")
//    List<TransactionHistory> findTransactionsByUsername(@Param("sender_id") int sender_id);



//temporary change to command in order to replace jpql query whit a join table to display dto object
//@Query("select t FROM Transaction t ")
//    List<Transaction> findTransaction();



    // this is native query works on database should convert to jpql quarry:
    // " SELECT t.sender_id,t.receiver_id, t.description, t.amount
    //           FROM transaction t
    //            JOIN user u ON t.receiver_id = u.id
    //            WHERE t.sender_id = 1; "
//    @Query(" SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory " +
//            "(t.sender AS sender_id, t.receiver AS receiver_id, t.description, t.amount)" +
//            "FROM Transaction t" +
//            "WHERE t.id = 1 ")
    @Query("select new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id ,t.receiver.username, t.description, t.amount)" +
            "            FROM Transaction t  " +
            "            WHERE (t.sender.id= :sender_id " +
            "            AND t.receiver.id= :receiver_id )")
    List<TransactionHistory> findTransaction(@Param("sender_id")int senderId,@Param("receiver_id")int receiverId);


//@Query("select new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id,t.receiver,t.description,t.amount) FROM TransactionHistory t ")
//    TransactionHistory findAllTransactionHistory();

//    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.sender, t.receiver, t.description, t.amount) " +
//            "FROM Transaction t " +
//            "JOIN User u ON t.receiver = u.id " +
//            "WHERE u.username = :username")

//    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id,t.receiver.username, t.description, t.amount) " +
//            "FROM Transaction t " +
//            "WHERE t.receiver.username = :username")
//    List<TransactionHistory> findTransactionsByUsername(@Param("username") String username);


//    @Query(value = "select t.id,t.date,t.fee,t.receiver_id,t.sender_id, t.description, t.amount" +
//            "            FROM transaction t  " +
//            "            WHERE t.sender_id= :sender_id ",nativeQuery = true)


    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.TransactionHistory(t.id ,r.username, t.description, t.amount)" +
            "            FROM Transaction t  " +
            "            JOIN t.receiver r   " +
            "            WHERE t.sender.id= :senderId ")
    List<TransactionHistory> findAllTransaction(@Param("senderId")int senderId);

}
