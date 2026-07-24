package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.entity.AccountStatus;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.exception.SuccessException;
import com.example.enotes_api_service.repo.UserRepo;
import com.example.enotes_api_service.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HomeServiceImpl implements HomeService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public Boolean verifyAccount(Integer userId, String verifyCode) throws Exception {
        //if user with this id present then findby id else throw invalid user
        User user = userRepo
                .findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("invalid user"));
        //if verification code == null then user already vrified
        if(user.getStatus().getVerificationCode()==null){
            throw new SuccessException("Account already verified");

        }

        //if user with that id have same verification code then set verification code to null

        if(user.getStatus().getVerificationCode().equals(verifyCode)){
            AccountStatus status = user.getStatus();
            status.setIsActive(true);
            status.setVerificationCode(null);
            userRepo.save(user);
            return true;

        }
        return false;
    }
}
