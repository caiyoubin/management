package com.example.web.exception;


import com.example.common.code.StateCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ExceptionAdvice {

    // 统一异常输出
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<HttpExceptionResponse> handleException(HttpServletRequest request, Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final HttpExceptionResponse response = new HttpExceptionResponse(StateCode.FAIL, e.getMessage(), request.getRequestURI());
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response, httpHeaders, badRequest);
    }
}
