package com.ykeshtdar.demoP6.service;

import com.ykeshtdar.demoP6.model.User;
import com.ykeshtdar.demoP6.repository.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//       User user = userRepository.findByUsername(username)
//               .orElseThrow(()->new UsernameNotFoundException("user did not find"));

        Optional<User> optional = userRepository.findByUsername(username);
        User currentUser = null;
        if (optional.isPresent()) {
            currentUser = optional.get();
            UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                    .username(currentUser.getUsername())
                    .password(currentUser.getPassword())
                    .roles(currentUser.getRole())
                    .build();
            return userDetails;
        } else {
            throw new UsernameNotFoundException(username);
        }
    }
}
