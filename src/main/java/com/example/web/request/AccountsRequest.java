package com.example.web.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class AccountsRequest {

    @NotNull
    private Integer id;
    @JsonProperty("material_cost")
    private Double materialCost;
    @JsonProperty("labor_cost")
    private Double laborCost;
    @JsonProperty("traffic_expense")
    private Double trafficExpense;
    @JsonProperty("other_expense")
    private Double otherExpense;
    @JsonProperty("item_price")
    private Double itemPrice;
    @JsonProperty("is_bill")
    private Boolean isBill;
    @JsonProperty("is_return")
    private Boolean isReturn;
    private String remarks;

}
