package com.ykeshtdar.demoP6.repository;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository <User,Integer> {

     Optional<User> findByEmail(String email);
    Optional<User> findById(int id);



    @Query("SELECT new com.ykeshtdar.demoP6.model.dto.Beneficiary(f.id, u.id, f.id) " +
            "FROM User u " +
            "JOIN u.userSet f " +
            "WHERE u.id = :userId")
    List<Beneficiary> findAllFriendsByUserId(@Param("userId") Integer userId);


}




