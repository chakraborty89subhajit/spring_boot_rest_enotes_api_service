package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.PasswordChangeRequest;

public interface UserService {
    public void changePassword(PasswordChangeRequest passwordChangeRequest);
}
