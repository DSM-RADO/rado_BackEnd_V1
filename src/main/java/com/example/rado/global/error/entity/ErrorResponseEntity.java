package com.example.rado.global.error.entity;

import com.example.rado.global.error.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
@Builder
public class ErrorResponseEntity {

    private int status;
    private String code;
    private String message;

    public static ResponseEntity<ErrorResponseEntity> responseEntity(ErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(ErrorResponseEntity.builder()
                        .status(errorCode.getHttpStatus())
                        .code(errorCode.name())
                        .message(errorCode.getMessage())
                        .build()
                );
    }
}
