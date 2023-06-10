package com.example.rado.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class SignRequest {

    private String userId;

    private String password;

    private String name;
}
