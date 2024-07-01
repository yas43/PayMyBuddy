package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.model.dto.*;
import com.ykeshtdar.demoP6.model.dto.TransactionHistory;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

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

//    public List<TransactionHistory> displayCurrentUserTransactionHistory(int senderId, int receiverId){
//
//        return transactionRepository.findTransactions(senderId,receiverId);
//    }

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

    public User updateuser(User user){

        User user1 = userRepository.findByUsername(user.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("there is no this user"));

        int id = user1.getId();

//        int id =userRepository.findByUsername(user.getUsername()).getId();

//      User actueluser = userRepository.findById(id);
        Optional<User> optional = Optional.ofNullable(userRepository.findById(id));
        User actualuser = null;
//        User actualuser = new User();
        if (optional.isPresent()){

            actualuser = optional.get();

            actualuser.setUsername(user.getUsername());
            actualuser.setPassword(user.getPassword());
            actualuser.setEmail(user.getEmail());
            userRepository.save(actualuser);
        }
        else {
            throw new RuntimeException("user do not exist");
        }
        return user;

    }

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

    public User logIn(String email){
        User currentUser = userRepository.findByEmail(email);
        if (currentUser.getPassword() != null ){
            currentUser.setUsername(userRepository.findByEmail(email).getUsername());
        }
        else {
            throw new RuntimeException("user dose not exist");
        }
        return currentUser;
    }

//    public List<User> showalluserusingquery(){
//        return userRepository.findalluser();
//    }
//
//    public List<TransactionHistory> showalltransaction(int sender_id){
//        return  transactionRepository.findTransactionsByUsername(sender_id);
//
//    }

//    public List<TransactionHistory> showalltransaction(){
//        return transactionRepository.findTransactionsByUsername("yaser");
//    }

//public TransactionHistory showalltransactionhistory(){
//        return transactionRepository.findAllTransactionHistory();
//}


    public List<TransactionHistory> findalltransaction(int senderId,int receiverId){
        return transactionRepository.findTransaction(senderId,receiverId);
    }



}
