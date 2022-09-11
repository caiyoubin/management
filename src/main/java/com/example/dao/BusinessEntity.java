package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "business")
public class BusinessEntity extends BaseEntity{

    private String itemId;
    private String itemName;
    private Integer customerId;
    private String number;
    private String demandType;
    private String currentState;
    private String productType;
    private String material;
    private String workmanship;
    private Integer width;
    private Integer height;
    private Integer thickness;
    private String imageUrl;
    private String remarks;
    private Boolean cancel;
    private Date completionTime;
    private String userId;

}
