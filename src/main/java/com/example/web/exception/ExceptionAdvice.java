package com.example.web.exception;


import com.example.common.code.StateCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j(topic = "ExceptionAdvice")
@ControllerAdvice
public class ExceptionAdvice {

    // 统一异常输出
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<HttpExceptionResponse> handleException(HttpServletRequest request, Exception e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        final HttpExceptionResponse response = new HttpExceptionResponse(StateCode.FAIL, e.getMessage(), request.getRequestURI());
        final HttpHeaders exceptionHeads = new HttpHeaders();
        exceptionHeads.setContentType(MediaType.APPLICATION_JSON);
        log.warn("", e);
        return new ResponseEntity<>(response, exceptionHeads, badRequest);
    }
}
