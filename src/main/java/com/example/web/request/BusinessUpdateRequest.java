package com.example.web.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class BusinessUpdateRequest {

    @NotNull
    private Integer id;
    private String number;
    @JsonProperty("demand_type")
    private String demandType;
    @JsonProperty("current_state")
    private String currentState;
    @JsonProperty("product_type")
    private String productType;
    private String material;
    private String workmanship;
    private Integer width;
    private Integer height;
    private Integer thickness;
    private String remarks;

}
