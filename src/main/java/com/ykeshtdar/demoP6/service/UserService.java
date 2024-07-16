package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.model.dto.*;
import com.ykeshtdar.demoP6.model.dto.TransactionHistory;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.*;

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
        if (isValidEmail( user.getEmail()) && !isExistAlready(user.getEmail())) {
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("email is not valid or already exist");
        }
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

//    public User updateUser(){
//        User curentUser = getconnectedUser();
//        curentUser.setBalance(curentUser.getBalance());
//        curentUser.setUserSet(curentUser.getUserSet());
//        curentUser.setPassword(curentUser.getPassword());
//        curentUser.setEmail(curentUser.getEmail());
//
//    }



    public User updateuser(User user){

        User currentUser = getconnectedUser();
        int id = currentUser.getId();



//        User user1 = userRepository.findByUsername(user.getUsername())
//                .orElseThrow(()->new UsernameNotFoundException("there is no this user"));
//
//        int id = user1.getId();

//        int id =userRepository.findByUsername(user.getUsername()).getId();

//      User actueluser = userRepository.findById(id);
        Optional<User> optional = userRepository.findById(id);
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
        Optional<User>optional = userRepository.findByEmail(email);
         User user = null;


        if (optional.isPresent() ){
            user = optional.get();
        }
        else {
            throw new RuntimeException("user dose not exist");
        }
        return user;

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
    public User addBeneficiary(String email){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principle = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principle;
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("user did not find"));

        if (isValidEmail(email) && isExistAlready(email) && !(user.getEmail().equals(email))) {

            User beneficiary = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("this email dose not exist"));

            user.getUserSet().add(beneficiary);
            return userRepository.save(user);
        }
        else {
            throw new RuntimeException("this email is not valid");
        }

    }

    private boolean isValidEmail(String email){
//        Pattern pattern = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$");
//        Matcher match = pattern.matcher(email);
//        return match.hasMatch();
        return true;

    }
    private boolean isExistAlready(String email){
//        Optional<User>optional = userRepository.findByEmail(email);
//        if (optional.isPresent()){
//            return true;
//        }
//        else {
//            return false;
//        }
        return false;

    }

    public User getconnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principle = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principle;
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("user did not find"));
        System.out.println("hello this is current user username "+user.getUsername());
        return user;

    }





}
