package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String getMethodName(
            @Validated CalculatorDto calculatorDto,
            Model view) {
                // cast string to char
        float result = this.calculatorService.resolveOperation(calculatorDto.getNum1(), calculatorDto.getNum2(), calculatorDto.getOp());
        String resulString = String.format("%.2f %s %.2f = %.2f", calculatorDto.getNum1(), calculatorDto.getOp(), calculatorDto.getNum2(), result);
        view.addAttribute("data", resulString);
        return "calculator";
    }

    @GetMapping("eval")
    public String getMethodName(
            @RequestParam(name = "exp", required = false, defaultValue = "2+3=") String exp,
            Model view) {
        String result = this.calculatorService.eval(exp);
        view.addAttribute("data", result);
        return "calculator";
    }

}
