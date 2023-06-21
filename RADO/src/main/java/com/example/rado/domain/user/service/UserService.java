package com.example.rado.domain.user.service;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.controller.dto.response.TokenResponse;
import com.example.rado.domain.user.entity.User;
import com.example.rado.domain.user.repository.UserRepository;
import com.example.rado.domain.user.service.Faeade.UserFacade;
import com.example.rado.global.security.jwt.JwtProperties;
import com.example.rado.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserFacade userFacade;
    private final JwtProperties jwtProperties;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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

    public TokenResponse userLogin(UserLoginRequest request){


        User user = userRepository.findByUserId(request.getUserId())
                .orElseThrow();

        if (request.getUserId().equals(user.getUserId()) && request.getUserPassword().equals(user.getUserPassword())){
           return TokenResponse
                   .builder()
                   .accessToken(jwtTokenProvider.createAccessToken(user.getUserId()))
                   .expiredAt(java.time.LocalDateTime.now()
                           .plusSeconds(jwtProperties.getAccessExpiration()))
                   .build();

        }
        else {
            throw new IllegalArgumentException("비밀번호 또는 아이디가 일치하지 않습니다");
        }
    }

    @Transactional
    public void userRemove() {
        User user = userFacade.currentUser();
        if (user != null) {
            userRepository.deleteById(user.getId());
        } else {
            throw new IllegalStateException("현재 사용자를 찾을 수 없습니다.");
        }
    }

}
