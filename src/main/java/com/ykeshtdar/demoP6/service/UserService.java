package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.*;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.stereotype.*;

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
}
