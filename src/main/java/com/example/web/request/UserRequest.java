package com.example.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    @NotBlank(message = "userId can not be blank")
    private String userId;
    @NotBlank(message = "password can not be blank")
    private String password;

}
