package com.example.rado.domain.user.presentation;

import com.example.rado.domain.user.presentation.dto.request.Duplicate;
import com.example.rado.domain.user.presentation.dto.request.LoginRequest;
import com.example.rado.domain.user.presentation.dto.request.SignUpRequest;
import com.example.rado.domain.user.service.DeleteUserService;
import com.example.rado.domain.user.service.DuplicateService;
import com.example.rado.domain.user.service.LoginUserService;
import com.example.rado.domain.user.service.SignupUserService;
import com.example.rado.global.security.jwt.dto.TokenResponse;
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
    private final SignupUserService signupUserService;
    private final LoginUserService loginUserService;
    private final DeleteUserService deleteUserService;
    private final DuplicateService duplicateService;

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

    @Operation(summary = "아이디 중복확인")
    @GetMapping("/duplicate")
    public boolean duplicate(Duplicate duplicate){
        return duplicateService.idDuplicate(duplicate);
    }
}
