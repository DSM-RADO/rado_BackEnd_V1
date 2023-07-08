package com.example.backend.domain.user.domain;

import com.example.backend.domain.user.enums.Role;
import com.example.backend.global.entity.BaseEntity;
import com.example.backend.infrastructure.aws.image.DefaultImage;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
@Table(name = "tbl_user")
public class User extends BaseEntity {

    private String email; // 이메일

    private String accountId; // 아이디

    private String nickName; // 닉네임

    @Length(max = 68)
    private String password; // 비밀번호

    private String profile; // 이미지

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String email, String accountId, String nickName, String password) {
        this.email = email;
        this.accountId = accountId;
        this.nickName = nickName;
        this.password = password;
        this.profile = DefaultImage.USER_PROFILE_IMAGE;
        this.role = Role.ROLE_USER;
    }

    public void userProfileChange(String profile){this.profile = profile;}
}
