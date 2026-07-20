package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.UserDTO;
import com.example.enotes_api_service.entity.Role;
import com.example.enotes_api_service.entity.User;
import com.example.enotes_api_service.repo.RoleRepo;
import com.example.enotes_api_service.repo.UserRepo;
import com.example.enotes_api_service.service.UserService;
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

    @Override
    public Boolean register(UserDTO userDto) {

        validation.userValidation(userDto);
        User user = mapper.map(userDto,User.class);
        setRole(userDto,user);
         User saveUser = userRepo.save(user);
         if(!ObjectUtils.isEmpty(saveUser)){
             return true;
         }
        return false;
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
