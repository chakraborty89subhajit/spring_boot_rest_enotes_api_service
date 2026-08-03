package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.service.HomeService;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/home")
public class HomeController {
    @Autowired
    private HomeService homeService;

    @Autowired
    private UserService userService;

    @GetMapping("/verify")
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

    @GetMapping("/send-email-reset")
    public ResponseEntity<?> sendEmailForPasswordreset(@RequestParam String email,
                                                       HttpServletRequest request)throws Exception
    {
        userService.sendEmailPasswordReset(email,request);

        return CommonUtil.createBuildResponseMessage("email for password reset send successfully",
                HttpStatus.OK);
    }

    @GetMapping("/verify-password-link")
    public ResponseEntity<?> verifyPasswordResetLink(){

        return null;
    }

    @GetMapping("/reset-password")
    public ResponseEntity<?> resetPassword(){

        return null;
    }

}
