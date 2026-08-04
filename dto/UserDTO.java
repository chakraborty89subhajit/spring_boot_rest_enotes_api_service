package com.example.enotes_api_service.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String mobNo;
    private String password;
    private List<RoleDTO> roles;


    //inner class

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    @Builder
    public static class RoleDTO{
        private Integer id;
        private String name;
    }

}
