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
    public User callyaser_temp(){
        Optional<User> optional = userRepository.findById(1);
        User user = null;
        if (optional.isPresent()){
            user=optional.get();
        }
        else {throw new RuntimeException("can not find user");}

        return user;
    }

    public User updateuser(User user){
        return userRepository.save(user);
    }

    public User callUserById(int id){
       Optional<User> optional = userRepository.findById(id);
       User user = null;
       if (optional.isPresent()){
           user = optional.get();
       }
       else {
           throw new RuntimeException("user do not exist");
       }
       return user;
    }

}
