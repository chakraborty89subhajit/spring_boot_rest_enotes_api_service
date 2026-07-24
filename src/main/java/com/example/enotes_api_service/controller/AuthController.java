package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.UserDTO;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v1/user")
public class AuthController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<?> registerUser(@RequestBody UserDTO userDTO, HttpServletRequest request) throws Exception{

        String url= CommonUtil.getUrl(request);

        Boolean register = userService.register(userDTO,url);
        if(register){
            return CommonUtil.createBuildResponseMessage(
                    "register success", HttpStatus.CREATED);
        }
        return CommonUtil.createErrorResponseMessage(
                "register failed",HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
