package com.example.web.vo;

import com.example.dao.BusinessEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Data
public class BusinessVo {

    private Integer id;
    private String itemName;
    private String customerName;
    private Integer number;
    private String demandType;
    private String currentState;
    private String productType;
    private String material;
    private Integer width;
    private Integer height;
    private Integer thickness;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date completionTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date updateTime;
    private String nickname;

    public void CopyEntity(BusinessEntity entity, String customerName) {
        BeanUtils.copyProperties(entity, this);
        this.customerName = customerName;
    }

}
