package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.SignupRequest;
import com.example.rado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping(" ")
    public void userAdd(
            @RequestBody SignupRequest request
            ) {
        userService.userAdd(request);
    }
}
