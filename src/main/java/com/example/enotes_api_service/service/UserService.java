package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.PasswordChangeRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public void changePassword(PasswordChangeRequest passwordChangeRequest);

    void sendEmailPasswordReset(String email, HttpServletRequest request) throws Exception;
}
