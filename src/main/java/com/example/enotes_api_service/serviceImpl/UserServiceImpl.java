package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.config.security.CustomUserDetails;
import com.example.enotes_api_service.dto.EmailRequest;
import com.example.enotes_api_service.dto.LoginRequest;
import com.example.enotes_api_service.dto.LoginResponse;
import com.example.enotes_api_service.dto.UserDTO;
import com.example.enotes_api_service.entity.AccountStatus;
import com.example.enotes_api_service.entity.Role;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.repo.RoleRepo;
import com.example.enotes_api_service.repo.UserRepo;
import com.example.enotes_api_service.service.UserService;
import com.example.enotes_api_service.util.EmailSend;
import com.example.enotes_api_service.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import sun.text.normalizer.ICUBinary;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
     private RoleRepo roleRepo;

    @Autowired
    private Validation validation;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailSend emailSend;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Boolean register(UserDTO userDto,String url) throws Exception {

        validation.userValidation(userDto);
        User user = mapper.map(userDto,User.class);

        setRole(userDto,user);
        //setimg user status
        AccountStatus status =  AccountStatus.builder()
                .isActive(false)
                .verificationCode(UUID.randomUUID().toString())
                .build();

        user.setStatus(status);
        //encode the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

         User saveUser = userRepo.save(user);
         if(!ObjectUtils.isEmpty(saveUser)){
             emailSend(saveUser,url);
             return true;
         }
        return false;
    }



    private void emailSend(User user,String url) throws Exception{

        String message= "Hi <b>[[userName]]</b>"+
                "<b>register successfully done</b>"+
                "<a href=\'[[url]]\'>click here</a>";

        message = message.replace("[[userName]]",user.getFirstName());
        message=message.replace("[[url]]",url+
                "/api/v1/home/verify?uid="
                        +user.getId()
                        +"&code="+user.getStatus().getVerificationCode());

        EmailRequest emailReq = new EmailRequest();
        emailReq.setTo(user.getEmail());
        emailReq.setSubject("Registration Successful");
        emailReq.setTitle("eNotes Support");
        emailReq.setMessage(message);

        emailSend.send(emailReq);

    }
    private void setRole(UserDTO userDTO,User user){
        List<Integer> roleRegId= userDTO
                .getRoles()
                .stream()
                .map(r->r.getId())
                .collect(Collectors.toList());

        List<Role> roles = roleRepo.findAllById(roleRegId);
        user.setRoles(roles);
    }
/**
    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager
                                     .authenticate(new UsernamePasswordAuthenticationToken(

                                             request.getEmail(),request.getPassword()));
        if(authentication.isAuthenticated()){
            CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getCredentials();
            String token = "demo token";
            LoginResponse loginResponse = LoginResponse.builder()
                    .userDTO(mapper.map(customUserDetails.getUser(),UserDTO.class))
                    .token(token)
                    .build();

            return loginResponse;

        }
        return null;
    }
    **/

@Override
public LoginResponse login(LoginRequest request) {

    Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));

    if (authentication.isAuthenticated()) {
        CustomUserDetails customUserDetails = (CustomUserDetails) authentication.getPrincipal();

        UserDTO userDTO = null;
        if (customUserDetails != null && customUserDetails.getUser() != null) {
            userDTO = mapper.map(customUserDetails.getUser(), UserDTO.class);
        }

        return LoginResponse.builder()
                .userDTO(userDTO)
                .token("demo token")
                .build();
    }

    return null;
}

}
