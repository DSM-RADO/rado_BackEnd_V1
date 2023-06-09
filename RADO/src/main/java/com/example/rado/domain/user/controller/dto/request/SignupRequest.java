package com.example.rado.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignupRequest {

    private String userId;

    private String userPassword;

    private String userName;
}
