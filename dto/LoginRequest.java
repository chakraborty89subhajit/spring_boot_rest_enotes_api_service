package com.example.enotes_api_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginRequest {
    private String email;
    private String password;
}
