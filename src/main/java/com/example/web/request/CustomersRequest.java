package com.example.web.request;


import com.example.dao.CustomersEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;


@Data
public class CustomersRequest {

    private Integer id;
    @NotBlank(message = "公司名称不能为空")
    private String company;
    @NotBlank(message = "部门名称不能为空")
    private String department;
    @NotBlank(message = "客户名字不能为空")
    private String customerName;
    private Boolean gender = true;
    private String telephone;
    private String address;
    private String nickname;


    public CustomersEntity toEntity() {
        final CustomersEntity entity = new CustomersEntity();
        BeanUtils.copyProperties(this, entity);
        entity.setId(null);
        return entity;
    }
}
