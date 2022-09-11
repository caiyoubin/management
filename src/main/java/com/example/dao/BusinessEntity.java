package com.example.dao;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "business")
public class BusinessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    private Date completionTime;
    private Date updateTime;
    private String userId;

}
