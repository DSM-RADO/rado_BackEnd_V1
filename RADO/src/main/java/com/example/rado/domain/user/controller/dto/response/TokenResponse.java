package com.example.rado.domain.user.controller.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class TokenResponse {


    private String accessToken;

    private LocalDateTime expiredAt;
}
