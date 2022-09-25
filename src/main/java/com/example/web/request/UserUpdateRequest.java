package com.example.web.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class UserUpdateRequest {

    @NotNull (message = "id 不能为空")
    private  Integer id;
    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "用户名不能为空")
    private String nickname;
    private String telephone;
    private boolean edit;
    private boolean admin;

}