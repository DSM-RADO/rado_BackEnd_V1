package com.example.rado.domain.user.presentation;

import com.example.rado.domain.user.presentation.dto.request.LoginRequest;
import com.example.rado.domain.user.presentation.dto.request.ModifyRequest;
import com.example.rado.domain.user.presentation.dto.request.SignUpRequest;
import com.example.rado.domain.user.presentation.dto.response.InfoResponse;
import com.example.rado.domain.user.service.InfoService;
import com.example.rado.domain.user.service.LoginService;
import com.example.rado.domain.user.service.ModifyService;
import com.example.rado.domain.user.service.SignUpService;
import com.example.rado.global.security.Jwt.dto.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@Transactional
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final SignUpService signUpService;
    private final LoginService loginService;
    private final ModifyService modifyService;
    private final InfoService infoService;

    @PostMapping("/signup")
    public void signUp(SignUpRequest request){
        signUpService.userSignUp(request);
    }

    @Transactional(readOnly = true)
    @PostMapping("/login")
    public TokenResponse login(LoginRequest request){
        return loginService.userLogin(request);
    }

    @PutMapping("/modify")
    public void modify(ModifyRequest request){
        modifyService.userModify(request);
    }

    @Transactional(readOnly = true)
    @GetMapping("/info")
    public InfoResponse info(){
        return infoService.userInfo();
    }
}
