package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @GetMapping("/calculator")
    public String getMethodName(
            @RequestParam(name = "num1", required = false, defaultValue = "2.0") float num1,
            @RequestParam(name = "num2", required = false, defaultValue = "3.0") float num2,
            @RequestParam(name = "op", required = false, defaultValue = "+") String op,
            Model view) {
        float result = this.calculatorService.resolveOperation(num1, num2, op);
        String resulString = String.format("%.2f %s %.2f = %.2f", num1, op, num2, result);
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
