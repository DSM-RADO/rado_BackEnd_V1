package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.exception.PasswordMissMatchException;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.request.LoginRequest;
import com.example.rado.global.security.Jwt.JwtProvider;
import com.example.rado.global.security.Jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final JwtProvider jwtProvider;
    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;

    public TokenResponse userLogin(LoginRequest request){

        User user = userFacade.getUserByUserId(request.getUserId());

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw PasswordMissMatchException.EXCEPTION;
        }

        return jwtProvider.getToken(user);
    }
}
