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
@Table(name = "accounts")
public class AccountsEntity extends BaseEntity{

    private String itemName;
    private Double materialCost;
    private Double laborCost;
    private Double trafficExpense;
    private Double otherExpense;
    private Double itemPrice;
    private Boolean isBill=false;
    private Boolean isReturn=false;
    @CreationTimestamp
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date orderTime;

}
