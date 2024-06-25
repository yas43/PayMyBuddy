package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {
     User findByUsername(String username);

//    @Query(value = "select * from user",nativeQuery = true)
//    List<User> findalluser();

//    @Query( "select p from User p where p.username like'%yaser%'")
//    List<User> findalluser();


    @Query( "select p from User p ")
    List<User> findalluser();
}




