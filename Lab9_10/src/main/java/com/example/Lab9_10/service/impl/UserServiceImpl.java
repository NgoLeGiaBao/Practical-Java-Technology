package com.example.Lab9_10.service.impl;

import com.example.Lab9_10.entity.User;
import com.example.Lab9_10.repository.UserRepository;
import com.example.Lab9_10.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }


    @Override
    public User saveUser(User newUser) {

       if(userRepository.findByUsername(newUser.getUsername()).isPresent()){
           throw new RuntimeException("Username already exists");
       }

        return userRepository.save(newUser);
    }

}

