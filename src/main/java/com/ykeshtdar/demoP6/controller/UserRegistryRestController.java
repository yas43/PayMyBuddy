package com.ykeshtdar.demoP6.controller;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.crypto.password.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("registry")
public class UserRegistryRestController {
    private final UserRepository userRepository;
   @Autowired
    private PasswordEncoder passwordEncoder;

    public UserRegistryRestController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public User adduser(@RequestBody User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("hello i am here"+user.getEmail());
        return userRepository.save(user);
    }
    @PutMapping("/{userId}/friend/{friendId}")
    public User addFriend(@PathVariable int userId,@PathVariable int friendId){
        User user = userRepository.findById(userId);
        User friend = userRepository.findById(friendId);
        user.getUserSet().add(friend);
        return userRepository.save(user);
    }
}

