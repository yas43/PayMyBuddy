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
import org.springframework.security.crypto.password.*;
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
private PasswordEncoder passwordEncoder;



    @Autowired
    public UserService(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }


    public User createUser(User user){
        if (isValidEmail( user.getEmail()) && !isExistAlready(user.getEmail())) {
            user.setBalance(0);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole("USER");
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

        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
        if (isValidEmail(user.getEmail())){
          return  userRepository.save(currentUser);
        }
        else {
            throw new RuntimeException("email is not valid");
        }



//        Optional<User> optional = userRepository.findById(id);
//        User actualuser = null;
//        if (optional.isPresent()){
//
//            actualuser = optional.get();
//
//            actualuser.setUsername(user.getUsername());
//            actualuser.setPassword(user.getPassword());
//            actualuser.setEmail(user.getEmail());
//            userRepository.save(actualuser);
//        }
//        else {
//            throw new RuntimeException("user do not exist");
//        }
//        return user;

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

//    public User logIn(String email){
//        Optional<User>optional = userRepository.findByEmail(email);
//         User user = null;
//
//
//        if (optional.isPresent() ){
//            user = optional.get();
//        }
//        else {
//            throw new RuntimeException("user dose not exist");
//        }
//        return user;
//
//    }

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


    public List<TransactionHistory> findallTransaction(String email){
        int senderId = getconnectedUser().getId();
        User receiver = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("this email is not valid"));
        int receiverId = receiver.getId();
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

// This regular expression is provided by the OWASP validation regex repository to check the email validation:
//^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2, 7}
//  https://www.baeldung.com/
    public static boolean isValidEmail(String email){
//        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@( ?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2,7}");
//        Matcher match = pattern.matcher(email);
//        return match.hasMatch();
return true;

    }
    private boolean isExistAlready(String email){
        Optional<User>optional = userRepository.findByEmail(email);
        if (optional.isPresent()){
            return true;
        }
        else {
            return false;
        }
    }

    public    User getconnectedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principle = authentication.getPrincipal();
        UserDetails userDetails = (UserDetails) principle;
        User user = userRepository.findByEmail(userDetails.getUsername())
                .orElseThrow(()->new UsernameNotFoundException("user did not find"));
        System.out.println("hello this is current user username "+user.getUsername());
        return user;

    }





}
