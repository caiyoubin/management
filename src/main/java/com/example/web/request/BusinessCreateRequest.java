package com.example.web.request;


import com.example.dao.BusinessEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;


@Data
public class BusinessCreateRequest {

    @JsonProperty("item_name")
    private String itemName;
    @NotNull(message = "customer can not be null.")
    @JsonProperty("customer_id")
    private Integer customerId;
    @JsonProperty("item_id")
    private Integer itemId;
    private String number;
    @JsonProperty("demand_type")
    private String demandType;
    @JsonProperty("current_state")
    private String currentState;
    @JsonProperty("product_type")
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
