package com.example.rado.domain.user.controller;

import com.example.rado.domain.user.controller.dto.request.SignRequest;
import com.example.rado.domain.user.controller.dto.request.SignupRequest;
import com.example.rado.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @DeleteMapping("/remove/{userId}")
    public void userRemove(
            @PathVariable Long userId
    ) {
        userService.removeUser(userId);
    }
}
