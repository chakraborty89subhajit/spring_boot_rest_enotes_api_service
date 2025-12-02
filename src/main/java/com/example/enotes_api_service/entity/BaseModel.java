package com.example.enotes_api_service.entity;

import lombok.Data;

import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {
    private Boolean is_active;
    private Boolean is_deleted;
    private Integer created_by;
    private Date created_on;
    private Integer updated_by;
    private Integer updated_on;

}
