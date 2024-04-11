package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long UserId;
    private String Username;
    private String password;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", Username='" + Username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
