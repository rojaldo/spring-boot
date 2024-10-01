package com.example.demo.greeting;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GreetingController {

    @GetMapping("/greeting")
    public String getMethodName(
        @RequestParam(name = "msg", required = false, defaultValue = "Hello World") String message, 
        @RequestParam(name = "num", required = false, defaultValue = "2.0") float number,
        Model view) {
        view.addAttribute("data", message);
        view.addAttribute("num", number);
        return "greeting";
    }
    
    
    
}
