package com.example.rado.domain.user.domain;

import com.example.rado.global.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class User extends BaseEntity {

    private String accountId; // 아이디

    private String nickName; // 닉네임

    private Integer year;
    private Integer month;
    private Integer day;

    @Length(max = 68)
    private String password; // 비밀번호

    private String profile; // 이미지


    @Builder
    public User( String accountId, String nickName, String password, Integer year, Integer month, Integer day) {
        this.accountId = accountId;
        this.nickName = nickName;
        this.password = password;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public void userProfileChange(String profile){this.profile = profile;}
}
