package com.example.demo.calculator;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
@Slf4j
public class CalculatorRestController {

    @Autowired
    private CalculatorService calculatorService;


    @GetMapping("/calculator")
    public ResponseEntity<Map> getMethodName( @Validated CalculatorDto calculatorDto) {
        float result = calculatorService.resolveOperation(calculatorDto.getNum1(), calculatorDto.getNum2(), calculatorDto.getOp());
        String resultString = String.format("%.2f %s %.2f = %.2f", calculatorDto.getNum1(), calculatorDto.getOp(), calculatorDto.getNum2(), result);
        calculatorService.addOperation(resultString);
        return ResponseEntity.status(HttpStatus.OK).body(Map.of("num1", calculatorDto.getNum1(), "num2", calculatorDto.getNum2(), "op", calculatorDto.getOp(), "result", result, "resultString", resultString, "prev_operations", calculatorService.getOperations()));
    }

    @GetMapping("eval")
    public ResponseEntity<CalculatorDto> getMethodName(@RequestParam(name = "exp", required = false, defaultValue = "2+3=") String exp) {
        String result = calculatorService.eval(exp);
        calculatorService.addOperation(result);
        return ResponseEntity.status(HttpStatus.OK).body(CalculatorDto.builder()
            .resultString(result)
            .prevOperations(calculatorService.getOperations().toString())
            .build());
    }
    
    
}
