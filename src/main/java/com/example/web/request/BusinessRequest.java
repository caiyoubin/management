package com.example.web.request;


import com.example.dao.BusinessEntity;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;


@Data
public class BusinessRequest {

    private Integer id;
    private String itemName;
    private Integer number;
    private String demandType;
    private String customerName;
    private String currentState;
    private String productType;
    private String material;
    private Date completionTime;
    private Integer width;
    private Integer height;
    private Integer thickness;

    public BusinessEntity toEntity() {
        final BusinessEntity entity = new BusinessEntity();
        BeanUtils.copyProperties(this, entity);
        entity.setId(null);
        return entity;
    }
}
