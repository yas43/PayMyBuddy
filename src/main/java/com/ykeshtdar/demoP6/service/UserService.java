package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;


    @Autowired
    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }


    public User saveUserInfo(User user){

         return userRepository.save(user);
    }

    public List<TransactionHistory> displayCurrentUserTransactionHistory(int senderId,int receiverId){

        return transactionRepository.findTransactions(senderId,receiverId);
    }

//    public void updateUserInfo(){
//
//    }
//    public void addRelation(){
//
//    }
//    public void pay(){
//
//    }
//    public List<User> showAllUser(){
//        List<User> userList = userRepository.findAll();
//        return userList;
//    }
//    public User callyaser_temp(){
//        Optional<User> optional = userRepository.findById(1);
//        User user = null;
//        if (optional.isPresent()){
//            user=optional.get();
//        }
//        else {throw new RuntimeException("can not find user");}
//
//        return user;
//    }

//    public User updateuser(User user,int id){

//        int id =userRepository.findByUsername(user.getUsername()).getId();

//      User user = userRepository.findById(id);
//        Optional<User> optional = userRepository.findById(id);
//        User actualuser = null;
////        User actualuser = new User();
//        if (optional.isPresent()){
//
//            actualuser = optional.get();
//
//            actualuser.setUsername(user.getUsername());
//            actualuser.setPassword(user.getPassword());
//            actualuser.setEmail(user.getEmail());
//        }
//        else {
//            throw new RuntimeException("user do not exist");
//        }
//        return user;
//        return userRepository.save(actualuser);
//    }

//    public User callUserById(int id){
//       Optional<User> optional = userRepository.findById(id);
//       User user = null;
//       if (optional.isPresent()){
//           user = optional.get();
//       }
//       else {
//           throw new RuntimeException("user do not exist");
//       }
//       return user;
//    }
//    public User findUserByUsername(String username){
//       return userRepository.findByUsername(username);
//    }

//    public User loginuser(String username,String password){
//        User currentUser = userRepository.findByUsername(username);
//        if (currentUser != null){
//            currentUser.setUsername(userRepository.findByUsername(username).getUsername());
//        }
//        else {
//            throw new RuntimeException("user dose not exist");
//        }
//        return currentUser;
//    }

}
