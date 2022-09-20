package com.example.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "business")
public class BusinessEntity extends BaseEntity{

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
    private Boolean cancel;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderTime;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date completionTime;
    private String username;

}
