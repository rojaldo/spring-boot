package com.example.demo;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculatorDto {

    @Range(min = -1000, max = 1000)
    private float num1;

    @Range(min = -1000, max = 1000)
    private float num2;

    @Pattern(regexp = "[+\\-*/]")
    private String op;

    
}
