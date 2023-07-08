package com.example.onepiece.User.global.security.error.handler;

import com.example.onepiece.User.global.security.error.entity.ErrorResponseEntity;
import com.example.onepiece.User.global.security.error.exception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException customException){
        return ErrorResponseEntity.responseEntity(customException.getErrorCode());
    }
}
