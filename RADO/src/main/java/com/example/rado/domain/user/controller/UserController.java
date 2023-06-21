package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.controller.dto.request.UserRequest;
import com.example.rado.domain.user.controller.dto.response.TokenResponse;
import com.example.rado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping
    public void userAdd(@RequestBody @Valid UserAddRequest request){
        userService.addUser(request);
    }

    @PostMapping("/login")
    public TokenResponse userLogin(@RequestBody UserLoginRequest request){
        return userService.userLogin(request);
    }

    @DeleteMapping("/delete")
    public void userDelete(){
        userService.deleteUser();
    }
}


