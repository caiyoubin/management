package com.example.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "business")
public class BusinessEntity extends BaseEntity{

    private Integer itemId;
    private String itemName;
    private Integer customerId;
    private Integer number;
    private String demandType;
    private String currentState;
    private String productType;
    private String material;
    private Integer width;
    private Integer height;
    private Integer thickness;
    private String imageUrl;
    private String remarks;
    private Boolean cancel;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date completionTime;
    private String userId;

}
