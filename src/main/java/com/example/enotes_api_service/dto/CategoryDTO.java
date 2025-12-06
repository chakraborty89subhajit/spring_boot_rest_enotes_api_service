package com.example.enotes_api_service.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CategoryDTO {
    private Integer id;
    private String name;
    private String description;
    private Boolean is_active;
    private Integer created_by;
    private Date created_on;
    private Integer updated_by;
    private Date updated_on;

}
