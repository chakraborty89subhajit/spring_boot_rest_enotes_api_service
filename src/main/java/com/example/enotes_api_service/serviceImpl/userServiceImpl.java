package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.EmailRequest;
import com.example.enotes_api_service.dto.PasswordChangeRequest;
import com.example.enotes_api_service.dto.PasswordResetRequest;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.repo.UserRepo;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.CommonUtil;
import com.example.enotes_api_service.util.EmailSend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class userServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailSend emailsend;


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

    @Override
    public void sendEmailPasswordReset(String email, HttpServletRequest request) throws Exception {
        User user = userRepo.findByEmail(email);
        if (ObjectUtils.isEmpty(user)) {
            throw new ResourceNotFoundException("invalid email");
        }
        //generate unique password reset token
        String passwordResetToken = UUID.randomUUID().toString();
        user.getStatus().setPasswordResetToken(passwordResetToken);
        User updateUser = userRepo.save(user);
        String url = CommonUtil.getUrl(request);
        sendEmailRequest(updateUser, url);
    }



    private void sendEmailRequest(User updateUser, String url)throws Exception{

        String message = "Hi <b>[[userName]]</b><br>" +
                "you email request to reset password<br>" +
                "click the below link to change your password<br>" +
                "<a href='[[url]]'>click here</a>";

        message = message.replace("[[userName]]", updateUser.getFirstName());
        message = message.replace("[[url]]", url +
                "/api/v1/home/verify-password-link?uid="
                + updateUser.getId()
                + "&code=" + updateUser.getStatus().getPasswordResetToken());

        EmailRequest emailReq = new EmailRequest();
        emailReq.setTo(updateUser.getEmail());
        emailReq.setSubject("Password Reset Request");
        emailReq.setTitle("eNotes Support");
        emailReq.setMessage(message);

        emailsend.send(emailReq);
    }

    @Override
    public void verifyPasswordResetLink(Integer uid, String code) throws Exception {

        User user = userRepo.findById(uid)
                .orElseThrow(()->new ResourceNotFoundException("invalid user id"));
        verifyPasswordResetToken(user.getStatus().getPasswordResetToken(),code);
    }


    private void verifyPasswordResetToken(String existToken, String requestToken) {
        // 1. Request token must not be null or empty
        if (!StringUtils.hasText(requestToken)) {
            throw new IllegalArgumentException("token is missing");
        }

        // 2. Token in DB must exist (if null/empty, link was already used)
        if (!StringUtils.hasText(existToken)) {
            throw new IllegalArgumentException("password already reset or token expired");
        }

        // 3. Tokens must match
        if (!existToken.equals(requestToken)) {
            throw new IllegalArgumentException("invalid token");
        }

        // Success! Do nothing so execution continues smoothly.
    }

    @Override
    public void resetPassword(PasswordResetRequest passwordResetRequest) throws Exception {

        User user = userRepo.findById(passwordResetRequest.getUid())
                .orElseThrow(()->new ResourceNotFoundException("invalid User id"));
        String encodedPassword = passwordEncoder.encode(passwordResetRequest.getNewPassword());
        user.setPassword(encodedPassword);
        user.getStatus().setPasswordResetToken(null);
        userRepo.save(user);

    }

}
