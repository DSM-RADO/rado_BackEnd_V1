package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.UserAddRequest;
import com.example.rado.domain.user.controller.dto.request.UserDuplicate;
import com.example.rado.domain.user.controller.dto.request.UserLoginRequest;
import com.example.rado.domain.user.service.UserService;
import com.example.rado.global.security.jwt.dto.TokenResponse;
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

   @GetMapping("/idDuplicate")
    public void userDuplicate(@RequestBody UserDuplicate userDuplicate){
        userService.duplicateUser(userDuplicate);
   }

   @PostMapping("/login")
   public TokenResponse userLogin(@RequestBody UserLoginRequest request){
        return userService.login(request);
   }
}


