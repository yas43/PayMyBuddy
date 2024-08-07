package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.model.dto.*;
import com.ykeshtdar.demoP6.model.dto.TransactionHistory;
import com.ykeshtdar.demoP6.repository.*;
import jakarta.transaction.*;
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


    @Transactional
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



    public User updateuser(User user){

        User currentUser = getconnectedUser();

if (user.getPassword().equals(currentUser.getPassword())) {
    currentUser.setUsername(user.getUsername());
    currentUser.setEmail(user.getEmail());
    currentUser.setPassword(user.getPassword());
}
else {
    currentUser.setUsername(user.getUsername());
    currentUser.setEmail(user.getEmail());
    currentUser.setPassword(passwordEncoder.encode(user.getPassword()));
}


        if (isValidEmail(user.getEmail())){

          return  userRepository.save(currentUser);
        }
        else {
            throw new RuntimeException("email is not valid");
        }




    }

    private boolean isValidPassword(String password) {
        return true;
    }










    public List<TransactionHistory> findallTransaction(){
        int senderId = getconnectedUser().getId();
        return transactionRepository.findAllTransaction(senderId);

    }
    @Transactional
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

    public List<User> findAllFriends(){

        User user = getconnectedUser();
       int senderId = user.getId();
     List<Beneficiary> beneficiaries =  userRepository.findAllFriendsByUserId(senderId);
     List<User>userListOfFriendlist = new LinkedList<>();
     for (Beneficiary beneficiary:beneficiaries){
         User friend = userRepository.findById(beneficiary.getFriendId())
                 .orElseThrow(()->new RuntimeException("beneficary id do not exist"));
         userListOfFriendlist.add(friend);
     }
     return userListOfFriendlist;

    }

// This regular expression is provided by the OWASP validation regex repository to check the email validation:
//^[a-zA-Z0-9_+&*-] + (?:\\.[a-zA-Z0-9_+&*-] + )*@(?:[a-zA-Z0-9-]+\\.) + [a-zA-Z]{2, 7}
//  https://www.baeldung.com/
    public static boolean isValidEmail(String email){
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$",Pattern.CASE_INSENSITIVE);
        Matcher match = pattern.matcher(email);
        return match.matches();
//return true;

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
