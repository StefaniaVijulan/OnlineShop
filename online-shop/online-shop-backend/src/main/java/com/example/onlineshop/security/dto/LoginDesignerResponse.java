package com.example.onlineshop.security.dto;

import com.example.onlineshop.security.models.Designer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LoginDesignerResponse {
    private final String jwt;
    private final Designer designer;
}
