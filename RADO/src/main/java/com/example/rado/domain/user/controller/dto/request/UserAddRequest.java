package com.example.rado.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserAddRequest {
    private String userId;
    private String userPassword;
    private String userName;
    private Integer year;
    private Integer month;
    private Integer day;
}
