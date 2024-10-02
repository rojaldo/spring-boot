package com.example.demo.library.users;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserErrorDto implements IUserResonse {
    
    private String message;
    private int status;
}
