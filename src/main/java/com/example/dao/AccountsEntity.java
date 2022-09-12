package com.example.dao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString(callSuper = true)
@Entity
@Table(name = "accounts")
public class AccountsEntity extends BaseEntity{

    private Integer itemId;
    private Double materialCost;
    private Double laborCost;
    private Double trafficExpense;
    private Double otherExpense;
    private Double itemPrice;
    private Boolean isBill=false;
    private Boolean isReturn=false;
    private String remarks;
    private String userId;

}
