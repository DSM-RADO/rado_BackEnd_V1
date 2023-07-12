package com.example.rado.global.error.exception;

import com.example.rado.global.error.ErrorCode;

public class ExpiredTokenException extends CustomException{

    public static final CustomException EXCEPTION = new ExpiredTokenException();

    private ExpiredTokenException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
