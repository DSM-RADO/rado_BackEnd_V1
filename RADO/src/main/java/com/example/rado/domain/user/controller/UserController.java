package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserDuplicate;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.service.UserService;
import com.example.rado.global.security.jwt.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "UserAPI")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(summary = "회원가입")
    @PostMapping
    public void userAdd(@RequestBody @Valid UserAddRequest request){
        userService.addUser(request);
    }

    @Operation(summary = "아이디 중복확인")
   @GetMapping("/idDuplicate")
    public void userDuplicate(@RequestBody UserDuplicate userDuplicate){
        userService.duplicateUser(userDuplicate);
   }

   @Operation(summary = "로그인")
   @PostMapping("/login")
   public TokenResponse userLogin(@RequestBody UserLoginRequest request){
        return userService.login(request);
   }
}


