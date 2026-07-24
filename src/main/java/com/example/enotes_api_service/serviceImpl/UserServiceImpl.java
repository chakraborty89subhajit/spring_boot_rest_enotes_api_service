package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.EmailRequest;
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
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
}
