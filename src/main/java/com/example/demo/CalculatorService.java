package com.example.demo;

import org.springframework.stereotype.Service;

enum CalculatorState {
    INIT,
    FIRST_NUMBER,
    SECOND_NUMBER,
    RESULT,
    ERROR
}

@Service
public class CalculatorService {

    private float num1;
    private float num2;
    private char op;
    private float result;
    private CalculatorState currentState = CalculatorState.INIT;

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

    public String eval(String exp) {
        // get each character from the expression
        char[] chars = exp.toCharArray();
        String result = "";
        for (char c : chars) {
            // if c is a number, process it
            if (Character.isDigit(c)) {
                // cast to int and process
                this.processNumber(Integer.parseInt(String.valueOf(c)));
            } else {
                // if c is an operator, process it
                this.processSymbol(c);
            }
        }
        if (this.currentState == CalculatorState.RESULT) {
            result = String.format("%.2f %s %.2f = %.2f", this.num1, this.op, this.num2, this.result);
        } else {
            result = String.format("%s --> %s", exp, "ERROR");
        }
        this.resetCalculator();
        return result;
    }

    private void processNumber(int num) {
        switch (this.currentState) {
            case INIT:
                this.num1 = num;
                this.currentState = CalculatorState.FIRST_NUMBER;
                break;
            case FIRST_NUMBER:
                this.num1 = this.num1 * 10 + num;
                break;
            case SECOND_NUMBER:
                this.num2 = this.num2 * 10 + num;
                break;
            case RESULT:
                this.currentState = CalculatorState.ERROR;
                break;
            case ERROR:
                break;
            default:
                break;
        }
    }

    private void processSymbol(char c) {
        switch (this.currentState) {
            case INIT:
                this.currentState = CalculatorState.ERROR;
                break;
            case FIRST_NUMBER:
                if (this.isOperator(c)) {
                    this.op = c;
                    this.currentState = CalculatorState.SECOND_NUMBER;
                } else{
                    this.currentState = CalculatorState.ERROR;  
                }
                break;
            case SECOND_NUMBER:
                if (c == '=') {
                    this.result = this.resolveOperation(this.num1, this.num2, String.valueOf(this.op));
                    this.currentState = CalculatorState.RESULT;
                } else {
                    this.currentState = CalculatorState.ERROR;
                }
                break;
            case RESULT:
                this.currentState = CalculatorState.ERROR;
                break;
            case ERROR:
                break;
            default:
                break;
        }

    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private void resetCalculator() {
        this.num1 = 0;
        this.num2 = 0;
        this.op = ' ';
        this.result = 0;
        this.currentState = CalculatorState.INIT;
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