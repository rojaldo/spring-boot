package com.example.demo;

import org.hibernate.validator.constraints.Range;

import jakarta.validation.constraints.Pattern;

public class CalculatorDto {

    @Range(min = -1000, max = 1000)
    private float num1;

    @Range(min = -1000, max = 1000)
    private float num2;

    @Pattern(regexp = "[+\\-*/]")
    private String op;

    CalculatorDto() {
    }

    CalculatorDto (float num1, float num2, String op) {
        this.num1 = num1;
        this.num2 = num2;
        this.op = op;
    }

    public float getNum1() {
        return num1;
    }

    public void setNum1(float num1) {
        this.num1 = num1;
    }

    public float getNum2() {
        return num2;
    }

    public void setNum2(float num2) {
        this.num2 = num2;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }
}
