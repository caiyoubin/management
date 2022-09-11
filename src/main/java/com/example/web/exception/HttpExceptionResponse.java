package com.example.web.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HttpExceptionResponse {

    private int code;
    private String message;
    private String path;

}
