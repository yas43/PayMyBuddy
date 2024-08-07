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
        return userRepository.save(user);
    }
    @PutMapping("/{userId}/friend/{friendId}")
    public User addFriend(@PathVariable int userId,@PathVariable int friendId){
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("no user with this id"));
        User friend = userRepository.findById(friendId)
                        .orElseThrow(()->new RuntimeException("no user whit this id"));
        user.getUserSet().add(friend);
        return userRepository.save(user);
    }
    @PutMapping("{senderId}/receiver/{receiverId}")
    public Transaction addTransaction(@PathVariable int senderId,@PathVariable int receiverId){
        User senderUser = userRepository.findById(senderId)
                .orElseThrow(()->new RuntimeException("no user with this id"));
        User receiverUser = userRepository.findById(receiverId)
                .orElseThrow(()->new RuntimeException("no user with this id"));

        Transaction transaction = new Transaction();
        transaction.setSender(senderUser);
        transaction.setReceiver(receiverUser);
        return transactionRepository.save(transaction);

    }
    @GetMapping("{senderId}/alltransaction/{receiverId}")
    public List<TransactionHistory> showAllTransaction(@PathVariable int senderId, @PathVariable int receiverId){
        return  transactionRepository.findTransaction(senderId,receiverId);
    }
    @DeleteMapping("user")
    public void deleteUser(@RequestParam String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new RuntimeException("email is not correct"));

            userRepository.delete(user);


    }
}

