package com.example.web.vo;

import lombok.Data;

@Data
public class UserInfoVo {

    private String username;
    private String nickname;
    private Boolean admin;
    private Boolean edit;

}
