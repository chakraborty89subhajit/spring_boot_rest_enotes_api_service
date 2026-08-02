package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.PasswordChangeRequest;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.repo.UserRepo;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;


    @Override
    public void changePassword(PasswordChangeRequest passwordChangeRequest) {
        User loggedinUser = CommonUtil.getLoggedinUser();
        if(!passwordEncoder.matches(passwordChangeRequest.getOldPassword(),
                loggedinUser.getPassword())){
            throw new IllegalArgumentException("old password is incorrecct");
        }
        String encodedPassword = passwordEncoder.encode(passwordChangeRequest.getNewPassword());
        loggedinUser.setPassword(encodedPassword);
        userRepo.save(loggedinUser);

    }
}
