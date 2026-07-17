package com.example.enotes_api_service.dto;

import lombok.*;

import javax.persistence.Entity;
import java.util.Date;

@Builder
//@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class ToDoDTO {
    private Integer id;
    private String title;
   // private Integer status;
    private Integer created_by;
    private Date created_on;
    private Integer updated_by;
    private Integer  updated_on;
    private StatusDTO status;





    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @Setter
    public static class StatusDTO{
        private Integer id;
        private String name;
    }



}
