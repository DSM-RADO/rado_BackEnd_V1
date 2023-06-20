package com.example.rado.domain.user.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequest {
    private String userId;
    private String userNewPassword;
    private String userOldPassword;
    private String userName;
    private Integer month;
    private Integer day;
}
