package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.request.ModifyRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModifyService {

    private final UserFacade userFacade;

    public void userModify(ModifyRequest request){

        User user = userFacade.getCurrentUser();

        user.modify(request.getUserId(), request.getPassword(), request.getNickname());
    }
}
