package com.example.rado.global.security.jwt.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class TokenResponse {

    private String accessToken;

    private LocalDateTime expiredAt;
}
