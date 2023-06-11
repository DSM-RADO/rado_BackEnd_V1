package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.SignRequest;
import com.example.rado.domain.user.controller.dto.request.SignupRequest;
import com.example.rado.domain.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @PostMapping(" ")
    public void userAdd(
            @RequestBody @Valid SignupRequest request
            ) {
        userService.userAdd(request);
    }

    @PutMapping("/modify/{userId}")
    public void userModify(
            @RequestBody @Valid SignRequest request,
            @PathVariable Long userId
            ) {
        userService.modifyUser(request, userId);
    }

}
