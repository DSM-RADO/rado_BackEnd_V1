package com.example.rado.domain.user.service;

import com.example.rado.domain.user.domain.User;
import com.example.rado.domain.user.facade.UserFacade;
import com.example.rado.domain.user.presentation.dto.request.LoginRequest;
import com.example.rado.global.error.ErrorCode;
import com.example.rado.global.error.exeception.CustomException;
import com.example.rado.global.security.jwt.dto.TokenResponse;
import com.example.rado.global.security.jwt.repository.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginUserService {

    private final UserFacade userFacade;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;

    @Transactional
    public TokenResponse execute(LoginRequest request){
        User user = userFacade.getUserByAccountId(request.getAccountId());

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new CustomException(ErrorCode.PASSWORD_MISS_MATCHED);
        }

        return jwtProvider.getToken(user);
    }
}
