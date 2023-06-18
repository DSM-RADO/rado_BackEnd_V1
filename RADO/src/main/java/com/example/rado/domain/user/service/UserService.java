package com.example.rado.domain.user.service;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import com.example.rado.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private  JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void addUser(UserAddRequest request){
        String userId = request.getUserId();
        Optional<User> user = userRepository.findByUserId(userId);
        if (user.isEmpty()){
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
        else {
            throw new IllegalArgumentException("중복된 아이디 입니다");
        }
    }

    public String loginUser(UserLoginRequest request){
        if ("UserId".equals(request.getUserId()) && "UserPassword".equals(request.getUserPassword())){
            String token = jwtTokenProvider.generateToken(request.getUserId());
            return token;
        }
        else {
            throw new IllegalArgumentException("로그인 실패");
        }
    }

}
