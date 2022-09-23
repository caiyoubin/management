package com.example.web.request;


import lombok.Data;


@Data
public class AccountsRequest {

    private Integer id;
    private Double materialCost;
    private Double laborCost;
    private Double trafficExpense;
    private Double otherExpense;
    private Double itemPrice;
    private Boolean isBill;
    private Boolean isReturn;
    private String nickname;
    private String itemName;

}
