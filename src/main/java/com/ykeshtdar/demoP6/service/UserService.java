package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUserInfo(User user){

         return userRepository.save(user);
    }
    public void updateUserInfo(){

    }
    public void addRelation(){

    }
    public void pay(){

    }
    public List<User> showAllUser(){
        List<User> userList = userRepository.findAll();
        return userList;
    }
}
