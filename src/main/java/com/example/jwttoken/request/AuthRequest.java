package com.example.jwttoken.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthRequest {
    @NotBlank(message = "Please enter the username field")
    private String username;
    @NotBlank(message = "Please enter the password field")
    private String password;
}
