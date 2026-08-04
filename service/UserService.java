package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.PasswordChangeRequest;
import com.example.enotes_api_service.dto.PasswordResetRequest;

import javax.servlet.http.HttpServletRequest;

public interface UserService {
    public void changePassword(PasswordChangeRequest passwordChangeRequest);

    void sendEmailPasswordReset(String email, HttpServletRequest request) throws Exception;

    void verifyPasswordResetLink(Integer uid, String code) throws Exception;

    void resetPassword(PasswordResetRequest passwordResetRequest) throws Exception;
}
