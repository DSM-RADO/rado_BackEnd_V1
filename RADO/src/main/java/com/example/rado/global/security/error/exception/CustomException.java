package com.example.onepiece.User.global.security.error.exception;

import com.example.onepiece.User.global.security.error.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CustomException extends RuntimeException{
   private ErrorCode errorCode;
}
