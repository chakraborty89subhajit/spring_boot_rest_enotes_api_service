package com.example.enotes_api_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class NotesDTO {

    private Integer id;

    private String title;
    private String description;
    private CategoryDTO category;
    private Integer created_by;
    private Date created_on;
    private Integer updated_by;
    private Date update_on;


    //creating inner class for clean output
   @Getter
   @Setter
   @NoArgsConstructor
   @AllArgsConstructor
    public static class CategoryDTO{
        private Integer id;
        private String name;
    }
}
