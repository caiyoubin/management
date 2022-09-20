package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "customers")
public class CustomersEntity extends BaseEntity{

    private String company;
    private String department;
    private String customerName;
    private Boolean gender;
    private String telephone;
    private String address;
    private Boolean state = false;

}
