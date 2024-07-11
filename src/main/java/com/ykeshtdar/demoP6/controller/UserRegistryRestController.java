package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.model.dto.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("registry")
public class UserRegistryRestController {
    private final UserRepository userRepository;
    private final TransactionRepository transactionRepository;
   @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegistryRestController(UserRepository userRepository, TransactionRepository transactionRepository) {
        this.userRepository = userRepository;
        this.transactionRepository = transactionRepository;
    }

    @PostMapping("/user")
    public User adduser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        System.out.println("hello i am here"+user.getEmail());
        return userRepository.save(user);
    }
    @PutMapping("/{userId}/friend/{friendId}")
    public User addFriend(@PathVariable int userId,@PathVariable int friendId){
        User user = userRepository.findById(userId);
        User friend = userRepository.findById(friendId);
        user.getUserSet().add(friend);
        return userRepository.save(user);
    }
    @PutMapping("{senderId}/receiver/{receiverId}")
    public Transaction addTransaction(@PathVariable int senderId,@PathVariable int receiverId){
        User senderUser = userRepository.findById(senderId);
        User receiverUser = userRepository.findById(receiverId);
//        System.out.println("sender name = "+senderUser.getUsername());
//        System.out.println("receiver name = "+receiverUser.getUsername());
        Transaction transaction = new Transaction();
        transaction.setSender(senderUser);
        transaction.setReceiver(receiverUser);
        return transactionRepository.save(transaction);

    }
    @GetMapping("{senderId}/alltransaction/{receiverId}")
    public List<TransactionHistory> showAllTransaction(@PathVariable int senderId, @PathVariable int receiverId){
        return  transactionRepository.findTransaction(senderId,receiverId);
    }
}

