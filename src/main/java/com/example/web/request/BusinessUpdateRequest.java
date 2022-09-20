package com.example.web.request;


import lombok.Data;

import javax.validation.constraints.NotNull;


@Data
public class BusinessUpdateRequest {

    @NotNull
    private Integer id;
    private String number;
    private String demandType;
    private String currentState;
    private String productType;
    private String material;
    private String workmanship;
    private Integer width;
    private Integer height;
    private Integer thickness;
    private String remarks;

}
