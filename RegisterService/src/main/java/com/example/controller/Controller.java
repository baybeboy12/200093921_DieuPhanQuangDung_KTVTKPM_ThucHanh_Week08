package com.example.controller;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.services.UserService;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.Optional;

@RestController
@RequestMapping("/api/register")
public class Controller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService useService;
    private final Bucket bucket;

    public Controller () {
        Bandwidth limit = Bandwidth.classic(2, Refill.greedy(2, Duration.ofMinutes(1)));
        this.bucket = Bucket.builder()
                .addLimit(limit)
                .build();
    }
    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userRepository.save(user);
    }
    @GetMapping("/getone")
    public User getOneUser(long id){
        if (bucket.tryConsume(1)){
            return useService.getOne(id);
        }
        else {
            return  null;
        }

    }
    @GetMapping("/api/login/getcheck")
    public ResponseEntity<String> getUseByEmailPass(String email){
        User user = useService.getUseByEmailPass(email);
        return ResponseEntity.ok(user+"");
    }
}
