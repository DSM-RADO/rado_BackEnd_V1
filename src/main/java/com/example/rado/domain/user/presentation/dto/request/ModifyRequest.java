package com.example.rado.domain.user.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyRequest {

    private String userId;

    private String password;

    private String nickname;

}
