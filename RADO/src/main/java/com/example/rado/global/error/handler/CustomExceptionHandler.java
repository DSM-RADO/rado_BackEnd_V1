package com.example.rado.global.error.handler;

import com.example.rado.global.error.entity.ErrorResponseEntity;
import com.example.rado.global.error.exeception.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {
    @ExceptionHandler(CustomException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(CustomException e){
        return ErrorResponseEntity.responseEntity(e.getErrorCode());
    }
}
