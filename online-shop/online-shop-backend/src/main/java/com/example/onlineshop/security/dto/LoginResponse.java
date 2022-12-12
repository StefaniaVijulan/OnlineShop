package com.example.onlineshop.security.dto;

import com.example.onlineshop.security.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginResponse {
    private final String jwt;
    private final User user;
}
