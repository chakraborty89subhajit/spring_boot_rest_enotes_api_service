package com.example.enotes_api_service.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginResponse {
    private UserResponse userDTO;
    private String token;
}
