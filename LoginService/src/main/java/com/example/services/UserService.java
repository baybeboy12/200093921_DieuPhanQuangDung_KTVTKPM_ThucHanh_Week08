package com.example.services;

import com.example.models.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.SQLException;
import java.util.Optional;

@Service
public class UserService {

    @Retryable(maxAttempts=5, value = RuntimeException.class,
            backoff = @Backoff(delay = 2000, multiplier = 2))
    public ResponseEntity<String> checkUser(String email){

//        System.out.println(user.get());
            System.out.println("check log");
            RestTemplate restTemplate = new RestTemplate();
            final String  uri = "http://localhost:8082/api/register/api/login/getcheck?email="+email;
            String response
                    = restTemplate.getForObject(uri, String.class);
            return ResponseEntity.status(HttpStatus.OK).body(response);

    }
}
