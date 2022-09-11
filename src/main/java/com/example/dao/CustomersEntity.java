package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
public class CustomersEntity {

    @Id
    private Integer id;
    private String company;
    private String Department;
    private String customer;
    private Boolean gender;
    private String telephone;
    private String address;
    private Boolean state;
    private Date createTime;
    private Date updateTime;
    private String userId;

}
