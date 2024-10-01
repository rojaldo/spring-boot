package com.example.demo.calculator;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class CalculatorDto {

    @Range(min = -1000, max = 1000)
    private float num1;

    @Range(min = -1000, max = 1000)
    private float num2;

    @Pattern(regexp = "[+\\-*/]")
    private String op;

    private float result;

    private String resultString;

    @JsonProperty("prev_operations")
    private String prevOperations;

    
}
