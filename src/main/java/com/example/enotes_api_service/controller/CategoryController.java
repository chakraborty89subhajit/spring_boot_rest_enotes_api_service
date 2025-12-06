package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.CategoryResponse;
import com.example.enotes_api_service.entity.Category;
import com.example.enotes_api_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping("/save-category")
    public ResponseEntity<?> savecategory(@RequestBody CategoryDTO categoryDTO){
        Boolean saveCategory = categoryService.saveCategory(categoryDTO);
        if(saveCategory){
            return new ResponseEntity<>("category saved successfully",
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("not saved",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(){
        List<CategoryDTO> allCategories= categoryService.getAllCategory();
        if(CollectionUtils.isEmpty(allCategories)){
            return ResponseEntity.noContent().build();

        }else{
            return new ResponseEntity<>(allCategories,HttpStatus.OK);
        }
    }

    @GetMapping("/active-category")
    public ResponseEntity<?> getActiveCategory(){
        List<CategoryResponse> allcategories = categoryService.getActiveCategory();
        if(CollectionUtils.isEmpty(allcategories)){
            return ResponseEntity.noContent().build();
        }else{
            return new ResponseEntity<>(allcategories,HttpStatus.OK);
        }
    }


}
