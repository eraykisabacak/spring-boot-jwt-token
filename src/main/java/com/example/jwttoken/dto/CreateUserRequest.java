package com.example.jwttoken.dto;

import com.example.jwttoken.model.Role;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequest{
    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the name field")
    private String name;

    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the username field")
    private String username;

    @Size(min = 3, max = 37)
    @NotBlank(message = "Please enter the password field")
    private String password;

    @NotEmpty(message = "Please enter the authorities field")
    Set<Role> authorities;
}
