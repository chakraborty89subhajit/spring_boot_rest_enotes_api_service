package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.PasswordResetRequest;
import com.example.enotes_api_service.endpoint.HomeControllerEndpoint;
import com.example.enotes_api_service.service.HomeService;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController

public class HomeController implements HomeControllerEndpoint {
    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;

    @Override
    public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid,
                                               @RequestParam String code ) throws Exception{
        Boolean verifyAccount = homeService.verifyAccount(uid,code);

        if(verifyAccount){
            return CommonUtil.createBuildResponseMessage("account verification successful",
                    HttpStatus.OK);

        }
        return CommonUtil.createErrorResponseMessage("invalid verification link",
                HttpStatus.BAD_REQUEST);
    }

 @Override
    public ResponseEntity<?> sendEmailForPasswordreset(@RequestParam String email,
                                                       HttpServletRequest request)throws Exception
    {
        userService.sendEmailPasswordReset(email,request);

        return CommonUtil.createBuildResponseMessage("email for password reset send successfully",
                HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid,
                                                     @RequestParam String code) throws Exception{
        userService.verifyPasswordResetLink(uid,code);
        return CommonUtil.createBuildResponseMessage
                ("verification done successfully",HttpStatus.OK);


    }

    @Override
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest
                                                       passwordResetRequest)throws Exception{

        userService.resetPassword(passwordResetRequest);
        return CommonUtil.createBuildResponseMessage(
                "password reset done successfully",HttpStatus.OK);

    }

}
