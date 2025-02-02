package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.Transaction;
import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.repository.*;
import jakarta.transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.context.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.time.*;
import java.util.*;

@Service
public class TransactionService {
    private final static double fee = 0.005;  //capital letters

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final UserService userService;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository, UserRepository userRepository, UserService userService) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @Transactional
    public Transaction createTransaction( String email, String amount,String description) {
        float transformedamount = Float.parseFloat(amount);

        if (hasValidSender() && hasVaidAmount(transformedamount) && hasValidReceiver(email) ){
            User sender  = getconnectedUser();
            User receiver = userRepository.findByEmail(email)
                            .orElseThrow(()->new RuntimeException("receiverEmail is not exist in database"));

            transferMoney(receiver,transformedamount);
            Date date = Date.from(Instant.now());
            Transaction transaction = new Transaction();
            transaction.setSender(sender);
            transaction.setReceiver(receiver);
            transaction.setFee((float) (fee*transformedamount));
            transaction.setDate(date);
            transaction.setDescription(description);
            transaction.setAmount(transformedamount);
          return   transactionRepository.save(transaction);

        }
        else {
            throw new RuntimeException("some things goes wrong try a gain");
        }
    }

    private void transferMoney(User receiver, float amount) {
        User sender = getconnectedUser();
        withdraw(sender, (float) ((1+fee)*amount));
        deposit(receiver,  amount);

    }

    private boolean hasValidReceiver(String email) {
        boolean beneficiaryHasValidEmail =  UserService.isValidEmail(email);
        User receiver = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("receiverEmail is not exist in database"));
        boolean beneficiaryIsInFriendList = userService.getconnectedUser().getUserSet().contains(receiver);

        if (beneficiaryHasValidEmail && beneficiaryIsInFriendList){
            return true;
        }
        else return false;
//        return true;
    }

    private boolean hasVaidAmount(float amount) {
        if (userService.getconnectedUser().getBalance()>=(1+fee)*amount && amount>0){
            return true;
        }
        else return false;
//        return true;
    }

    private boolean hasValidSender() {
//        User sender = userRepository.findById(senderId);
        return true;


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
    private User withdraw(User user,float amount){
        user.setBalance( user.getBalance()-amount);
        return userRepository.save(user);
    }

    private User deposit(User user,float amount){
        user.setBalance( user.getBalance()+amount);
        return userRepository.save(user);
    }


}
