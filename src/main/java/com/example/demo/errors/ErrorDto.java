package com.example.demo.errors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
        
        private String message;
        private String stackTrace;
        private int status;
    
}
