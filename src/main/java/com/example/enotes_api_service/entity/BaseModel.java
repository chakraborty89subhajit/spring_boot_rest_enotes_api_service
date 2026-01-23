package com.example.enotes_api_service.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseModel {

    //base model class will bw use to do auditing

   @CreatedBy
   @Column(updatable = false)
    private Integer created_by;

   @CreatedDate
   @Column(updatable = false)
    private Date created_on;

   @LastModifiedBy
   @Column(insertable = false)
    private Integer updated_by;


    @LastModifiedDate
    @Column(name = "updated_on",insertable = false)

    private Date updatedOn;
}
