package com.example.rado.domain.user.domain;

import com.example.rado.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseEntity {

    private String userId;

    private String password;

    private String nickname;

    @Builder
    public User(String userId, String password, String nickname){
        this.userId = userId;
        this.password = password;
        this.nickname = nickname;
    }
}
