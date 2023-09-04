package com.example.reservation.exception.impl;

import com.example.reservation.exception.AbstractException;
import org.springframework.http.HttpStatus;

public class AlreadyExistUserException extends AbstractException {
    @Override
    public int getStatusCode() { return HttpStatus.BAD_REQUEST.value(); }

    @Override
    public String getMessage() {
        return "이미 존재하는 유저입니다, id는 ?입니다";
    }
}

