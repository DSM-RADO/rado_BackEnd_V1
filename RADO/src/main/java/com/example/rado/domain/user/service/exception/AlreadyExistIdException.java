package com.example.rado.domain.user.service.exception;

public class AlreadyExistIdException extends RuntimeException{
    private static final String MESSAGE = "이미 등록된 아이디 입니다";

    public AlreadyExistIdException(){
        super(MESSAGE);
    }
}
