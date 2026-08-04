package com.example.enotes_api_service.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponse {



        private Integer id;
        private String firstName;
        private String lastName;
        private String email;
        private String mobNo;

        private StatusDTO status;

        private List<com.example.enotes_api_service.dto.UserDTO.RoleDTO> roles;


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

        @AllArgsConstructor
        @NoArgsConstructor
        @Getter
        @Setter
        @Builder

        public static class StatusDTO{
            private Integer id;
            private Boolean isActive;
        }


    }
