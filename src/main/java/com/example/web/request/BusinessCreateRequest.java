package com.example.web.request;


import com.example.dao.BusinessEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;


@Data
public class BusinessCreateRequest {

    private String itemName;
    @NotNull(message = "customer can not be null.")
    private Integer customerId;
    private Integer itemId;
    private String number;
    private String demandType;
    private String currentState;
    private String productType;
    private String material;
    private String workmanship;
    private Integer width;
    private Integer height;
    private Integer thickness;
    private String remarks;

    public BusinessEntity toEntity() {
        final BusinessEntity entity = new BusinessEntity();
        BeanUtils.copyProperties(this, entity);
        entity.setId(null);
        return entity;
    }
}
