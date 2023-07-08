package com.example.backend.domain.user.presentation;

import com.example.backend.domain.user.presentation.dto.request.CodeRequest;
import com.example.backend.domain.user.presentation.dto.request.LoginRequest;
import com.example.backend.domain.user.presentation.dto.request.SignUpRequest;
import com.example.backend.domain.user.service.CodeService;
import com.example.backend.domain.user.service.DeleteUserService;
import com.example.backend.domain.user.service.LoginUserService;
import com.example.backend.domain.user.service.SignupUserService;
import com.example.backend.global.security.jwt.dto.TokenResponse;
import com.example.backend.infrastructure.mail.dto.MailRequest;
import com.example.backend.infrastructure.mail.service.MailService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "User", description = "User API 입니다.")
@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserController {
    private final MailService mailService;
    private final CodeService codeService;
    private final SignupUserService signupUserService;
    private final LoginUserService loginUserService;
    private final DeleteUserService deleteUserService;

    @Operation(summary = "회원가입 코드 전송")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/code")
    public void Code(@Valid @RequestBody MailRequest request) throws Exception {
        mailService.execute(request);
    }

    @Operation(summary = "회원가입 코드 인증")
    @PostMapping("/check")
    public boolean Code(@Valid @RequestBody CodeRequest request) {
        return codeService.execute(request);
    }

    @Operation(summary = "회원가입")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/signup")
    public void signup(@Valid @RequestBody SignUpRequest request) {
        signupUserService.execute(request);
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    public TokenResponse login(@Valid @RequestBody LoginRequest request){
        return loginUserService.execute(request);
    }

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/leave")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void leaveUser() {
        deleteUserService.execute();
    }
}
