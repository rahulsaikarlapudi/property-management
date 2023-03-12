package com.mycompany.propertymanagement.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CalculatorDTO {
    private Double num1;
    private Double num2;
    private Double num3;
    @JsonProperty("num41")
    private Double num4;
}
