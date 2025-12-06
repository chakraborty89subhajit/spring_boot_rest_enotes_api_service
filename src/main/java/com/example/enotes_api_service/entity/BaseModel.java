package com.example.enotes_api_service.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public class BaseModel {

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_deleted")
    private Boolean isDeleted;

    private Integer created_by;
    private Date created_on;
    private Integer updated_by;

    @Column(name = "updated_on")
    private Integer updatedOn;
}
