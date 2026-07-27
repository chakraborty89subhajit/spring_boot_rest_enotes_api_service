package com.example.enotes_api_service.service;

import com.example.enotes_api_service.entity.User;

public interface JwtService {


    public String generateToken(User user);
}
