package com.example.web;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class HttpCodeResponse {

    private int code;
    private String message;
    private Object data;

}
