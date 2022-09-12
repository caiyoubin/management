package com.example.web.request;


import com.example.dao.CustomersEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;


@Data
public class CustomersRequest {

    private Integer id;
    @NotBlank(message = "company can not be blank")
    private String company;
    private String department;
    @NotBlank(message = "customer can not be blank")
    private String customer;
    private Boolean gender;
    private String telephone;
    private String address;
    private Boolean state;


    public CustomersEntity toEntity() {
        final CustomersEntity entity = new CustomersEntity();
        BeanUtils.copyProperties(this, entity);
        entity.setId(null);
        return entity;
    }
}
