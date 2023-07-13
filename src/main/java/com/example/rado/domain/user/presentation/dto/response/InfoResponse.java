package com.example.rado.domain.user.presentation.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class InfoResponse {

    private String userId;

    private String nickname;

    public InfoResponse(String userId, String nickname){
        this.nickname = nickname;
        this.userId = userId;
    }

}
