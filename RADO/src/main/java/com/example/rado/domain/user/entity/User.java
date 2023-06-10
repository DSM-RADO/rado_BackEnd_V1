package com.example.rado.domain.user.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "password", nullable = false, length = 1000)
    private String password;

    @Column(name = "name", nullable = false, length = 4)
    private String name;

    @Builder
    public User(String userId, String password, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
    }

    public void update(String userId, String password, String name){
        this.userId = userId;
        this.password = password;
        this.name = name;
    }
}
