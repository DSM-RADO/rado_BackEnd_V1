package com.example.rado.domain.user.entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "user_id", length = 30, nullable = true)
    private String userId;

    @Column(name = "user_name", length = 4, nullable = true)
    private String userName;

    @Column(name = "user_password", nullable = false, length = 30)
    private String userPassword;

    @Column(name = "year")
    private Integer year = 2007;

    @Column(name = "month", length = 12)
    private Integer month;

    @Column(name = "day", length = 31)
    private Integer day;

    public void modify(String userId, String userName, String userPassword, Integer month, Integer day){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.month = month;
        this.day = day;
    }

    @Builder
    public User(String userId, String userName, String userPassword, final Integer year, Integer month, Integer day){
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.year = year;
        this.month = month;
        this.day = day;
    }
}
