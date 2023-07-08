package com.example.onepiece.User.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;

    private LocalDateTime expiredAt;
}
