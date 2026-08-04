package com.example.enotes_api_service.service;

import com.example.enotes_api_service.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtService {


    public String generateToken(User user);
    public String extractUserName(String token);
    public Boolean validateToken(String token, UserDetails usreDetails);
}
