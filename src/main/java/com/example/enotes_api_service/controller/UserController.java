package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.UserResponse;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.util.CommonUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(){
        User loggedinUser = CommonUtil.getLoggedinUser();
        UserResponse userResponse = modelMapper.map(loggedinUser, UserResponse.class);
        return CommonUtil.createBuildResponse(userResponse, HttpStatus.OK);
    }

}
