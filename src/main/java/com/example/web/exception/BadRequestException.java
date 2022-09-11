package com.example.web.exception;


import com.example.common.code.StateCode;

public class BadRequestException extends RuntimeException{

    private int code;

    public BadRequestException(String message) {
        super( message);
        this.code = StateCode.FAIL;
    }
}
