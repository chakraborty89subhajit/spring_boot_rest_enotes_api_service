package com.example.enotes_api_service.util;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.ToDoDTO;
import com.example.enotes_api_service.dto.UserDTO;
import com.example.enotes_api_service.enums.TodoStatus;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.exception.ValidationException;
import com.example.enotes_api_service.repo.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class Validation {

    @Autowired
    private RoleRepo roleRepo;


    public void categoryValidation(CategoryDTO categoryDTO) {

        Map<String, Object> error = new LinkedHashMap<>();

        if (ObjectUtils.isEmpty(categoryDTO)) {
            throw new IllegalArgumentException("Category object must not be null");
        }

        // name
        if (ObjectUtils.isEmpty(categoryDTO.getName())) {
            error.put("name", "name field is empty");
        } else {
            if (categoryDTO.getName().length() < 10) {
                error.put("name", "name length must be >= 10");
            }
            if (categoryDTO.getName().length() > 100) {
                error.put("name", "name length must be <= 100");
            }
        }

        // description
        if (ObjectUtils.isEmpty(categoryDTO.getDescription())) {
            error.put("description", "description field is empty");
        } else {
            if (categoryDTO.getDescription().length() < 10) {
                error.put("description", "description length must be >= 10");
            }
            if (categoryDTO.getDescription().length() > 100) {
                error.put("description", "description length must be <= 100");
            }
        }

        // is_active
        if (categoryDTO.getIs_active() == null) {
            error.put("is_active", "is_active must not be null");
        }

        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }
    }

    //todoValidation
    public void todoValidation(ToDoDTO todo) throws Exception{
        ToDoDTO.StatusDTO reqStatus = todo.getStatus();
        Boolean statusFound = false;
        for(TodoStatus st : TodoStatus.values()){
            if(st.getId().equals(reqStatus.getId())){
                statusFound= true;
            }

        }
        if(!statusFound){
            throw  new ResourceNotFoundException("invalid Status");
        }
    }

    //registartion validation
    //user validation
    public void  userValidation(UserDTO userDTO){
        //first name validation
        if(!StringUtils.hasText(userDTO.getFirstName())){
            throw new IllegalArgumentException("first name is invalid");
        }
        //lsat name validation
        if(!StringUtils.hasText(userDTO.getLastName())){
            throw new IllegalArgumentException("last name is invalid");

        }
        //email validation
        if(!StringUtils.hasText(userDTO.getEmail()) ||
            !userDTO.getEmail().matches(Constant.EMAIL_REGEX)){
            throw new IllegalArgumentException("email is invalid");
        }

        //mobile no validation
        if(!StringUtils.hasText(userDTO.getMobNo()) ||
            !userDTO.getMobNo().matches(Constant.MOB_NO_REGEX)){
            throw new  IllegalArgumentException("mobile no is invalid");
        }

        //role validation
        if(CollectionUtils.isEmpty(userDTO.getRoles())){
            throw new IllegalArgumentException("role is invalid");

        }else{
            List<Integer> roleIds= roleRepo
                                   .findAll()
                                   .stream()
                                    .map(r->r.getId())
                                    .collect(Collectors.toList());
            List<Integer> invalidRegRoleIds= userDTO
                    .getRoles()
                    .stream()
                    .map(r->r.getId())
                    .filter(roleId->!roleIds.contains(roleId))
                    .collect(Collectors.toList());

            if(!CollectionUtils.isEmpty(invalidRegRoleIds)){
                throw new IllegalArgumentException("role is invalid"+invalidRegRoleIds);

            }

        }



    }
}
