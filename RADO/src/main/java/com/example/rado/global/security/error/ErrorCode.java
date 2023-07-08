package com.example.rado.global.security.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    USER_NOT_FOUND(404, "User Not Found"),
    USER_ALREADY_EXISTS(409, "User Already Exists"),
    NICKNAME_ALREADY_EXISTS(409, "Nickname Already Exists"),

    INTERNAL_SERVER_ERROR(500, ""),

    JWT_EXPIRED(401, "Jwt Expired"),
    JWT_INVALID(401, "Jwt Invalid");

    private final Integer httpStatus;
    private final String message;
}
