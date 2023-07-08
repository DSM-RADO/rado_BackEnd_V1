package com.example.rado.domain.user.service;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserDuplicate;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import com.example.rado.domain.user.service.Faeade.UserFacade;
import com.example.rado.global.security.jwt.dto.TokenResponse;
import com.example.rado.global.security.jwt.repository.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void addUser(UserAddRequest request) {
        userRepository.save(
                User.builder()
                        .userId(request.getUserId())
                        .userPassword(request.getUserPassword())
                        .userName(request.getUserName())
                        .year(request.getYear())
                        .month(request.getMonth())
                        .day(request.getDay())
                        .build());
    }

    public boolean duplicateUser(UserDuplicate userDuplicate){
            return userFacade.existsByUserId(userDuplicate.getUserId());
    }

    public TokenResponse login(UserLoginRequest userLoginRequest){
        User user = userFacade.getUserByUserId(userLoginRequest.getUserId());

        String accessToken = jwtProvider.generateAccessToken(user.getUserId());

        if (passwordEncoder.matches(userLoginRequest.getUserPassword(), user.getUserPassword()) && userLoginRequest.getUserId() == user.getUserId()){
            return TokenResponse.builder()
                    .accessToken(accessToken)
                    .build();
        }
        else {
            throw new IllegalArgumentException("아이디 또는 비밀번호가 일치하지 않습니다");
        }
    }

}


