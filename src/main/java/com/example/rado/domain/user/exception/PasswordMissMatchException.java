package com.example.rado.domain.user.exception;

import com.example.rado.global.error.ErrorCode;
import com.example.rado.global.error.exception.CustomException;

public class PasswordMissMatchException extends CustomException {

    public static final CustomException EXCEPTION = new  PasswordMissMatchException();

    private PasswordMissMatchException(){
        super(ErrorCode.PASSWORD_MISS_MATCH);
    }
}
