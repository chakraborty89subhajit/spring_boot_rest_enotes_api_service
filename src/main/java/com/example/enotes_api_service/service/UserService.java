package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.LoginRequest;
import com.example.enotes_api_service.dto.LoginResponse;
import com.example.enotes_api_service.dto.UserDTO;

public interface UserService {
    public Boolean register(UserDTO userDto,String url) throws Exception;

   public  LoginResponse login(LoginRequest request);
}
