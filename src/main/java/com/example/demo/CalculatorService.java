package com.example.demo;

import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    public float resolveOperation(float num1, float num2, String op) {
        switch (op) {
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default:
                return 0;
        }
    }
    
}

/**
 * 
 * 234+35=
 * 2/-3=
 * -2+-3=
 * 2,4+3,5=
 * 2+3+4=
 */