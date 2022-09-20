package com.example.web.request;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AccountsRequest {

    @NotNull
    private Integer id;
    private Double materialCost;
    private Double laborCost;
    private Double trafficExpense;
    private Double otherExpense;
    private Double itemPrice;
    private Boolean isBill;
    private Boolean isReturn;

}
