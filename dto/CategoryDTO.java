package com.example.enotes_api_service.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class CategoryDTO {
    private Integer id;


    @NotBlank(message="name should not be blank")
    @Size(min=10,max=100,message="name should be greater then 10 and less then 100")
    //@Max(value=100)
    private String name;

    @NotBlank(message="description should not be blank")
    @Size(min=10,max=100,message="description should not be less then 10 and greater then 100")
    //@Max(value=100)
    private String description;

    @NotNull(message="is_active should not be blank")
    private Boolean is_active;
    private Integer created_by;
    private Date created_on;
    private Integer updated_by;
    private Date updated_on;

}
