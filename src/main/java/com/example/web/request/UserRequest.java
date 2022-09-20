package com.example.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserRequest {

    @NotBlank(message = "username can not be blank")
    private String username;
    @NotBlank(message = "password can not be blank")
    private String password;

}
