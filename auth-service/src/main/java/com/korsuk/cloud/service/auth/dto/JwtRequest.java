package com.korsuk.cloud.service.auth.dto;

import lombok.Data;

@Data
public class JwtRequest {
    private String username;
    private String password;
}