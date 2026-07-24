package com.example.enotes_api_service.service;

public interface HomeService {

    public Boolean verifyAccount(Integer userId,String verifyCode) throws Exception;

}
