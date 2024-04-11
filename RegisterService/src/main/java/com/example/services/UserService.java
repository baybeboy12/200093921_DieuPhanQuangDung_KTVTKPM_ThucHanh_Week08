package com.example.services;

import com.example.entity.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User getUseByEmailPass(String email){
       return userRepository.findByEmail(email).get();
    }

    public User getOne(long id){
        Optional<User> user = userRepository.findById(id);
        return user.get();
    }
}
