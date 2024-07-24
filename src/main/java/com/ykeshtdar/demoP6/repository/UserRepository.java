package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
     Optional<User> findByUsername(String username);
     Optional<User> findByEmail(String email);
    Optional<User> findById(int id);

//    @Query(value = "select * from user",nativeQuery = true)
//    List<User> findalluser();

//    @Query( "select p from User p where p.username like'%yaser%'")
//    List<User> findalluser();


//    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.Friends(u.id ,u.username, t.description, t.amount)" +
//            "            FROM Transaction t  " +
//            "            JOIN t.receiver r   " +
//            "            WHERE t.sender.id= :senderId ")
//    List<Friends> findAllTransaction(@Param("senderId")int senderId);

//    @Query("SELECT f1.id, f2.id " +
//            "FROM User u1 " +
//            "JOIN u1.userSet f1 " +
//            "JOIN User u2 ON f1.id = u2.id " +
//            "JOIN u2.userSet f2 " +
//            "WHERE u1.id = :senderUsernameId")
//    List<String> findFriendshipDetails(@Param("senderUsernameId") int senderUsernameiId);



    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.Beneficiary(f.id, u.id, f.id) " +
            "FROM User u " +
            "JOIN u.userSet f " +
            "WHERE u.id = :userId")
    List<Beneficiary> findAllFriendsByUserId(@Param("userId") Integer userId);



    @Query( "select p from User p ")
    List<User> findalluser();
}




