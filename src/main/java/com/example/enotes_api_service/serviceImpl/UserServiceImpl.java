package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.EmailRequest;
import com.example.enotes_api_service.dto.UserDTO;
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
    public Boolean register(UserDTO userDto) throws Exception {

        validation.userValidation(userDto);
        User user = mapper.map(userDto,User.class);
        setRole(userDto,user);
         User saveUser = userRepo.save(user);
         if(!ObjectUtils.isEmpty(saveUser)){
             emailSend(saveUser);
             return true;
         }
        return false;
    }

    private void emailSend(User user) throws Exception{
        String message= "Hi <b>"+user.getFirstName()+"</b>"+
                "<b>register successfully done</b>"+
                        "<a href='#'>click here</a>";

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
