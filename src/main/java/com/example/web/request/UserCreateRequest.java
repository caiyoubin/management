package com.example.web.request;

import com.example.dao.UsersEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;

@Data
public class UserCreateRequest {

    @NotBlank(message = "账号不能为空")
    private String username;
    @NotBlank(message = "用户名不能为空")
    private String nickname;
    private String telephone;
    private boolean edit;
    private boolean admin;

    public UsersEntity toEntity() {
        final UsersEntity entity = new UsersEntity();
        BeanUtils.copyProperties(this, entity);
        return entity;
    }
}