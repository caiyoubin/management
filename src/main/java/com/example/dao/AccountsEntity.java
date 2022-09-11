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
@Table(name = "accounts")
public class AccountsEntity extends BaseEntity{

    private Double material_cost;
    private Double labor_cost;
    private Double trafficExpense;
    private Double otherExpense;
    private Double item_price;
    private Boolean is_bill;
    private Boolean is_return;
    private String remarks;
    private String userId;

}
