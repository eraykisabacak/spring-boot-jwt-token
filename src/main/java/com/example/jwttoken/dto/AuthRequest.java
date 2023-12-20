package com.example.jwttoken.dto;

public record AuthRequest(
        String username,
        String password
) {
}
