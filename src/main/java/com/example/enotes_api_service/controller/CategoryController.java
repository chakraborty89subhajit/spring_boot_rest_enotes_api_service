package com.example.enotes_api_service.controller;

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
    public ResponseEntity<?> savecategory(@RequestBody Category category){
        Boolean saveCategory = categoryService.saveCategory(category);
        if(saveCategory){
            return new ResponseEntity<>("category saved successfully",
                    HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>("not saved",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categories")
    public ResponseEntity<?> getAllCategory(){
        List<Category> allCategories= categoryService.getAllCategory();
        if(CollectionUtils.isEmpty(allCategories)){
            return ResponseEntity.noContent().build();

        }else{
            return new ResponseEntity<>(allCategories,HttpStatus.OK);
        }
    }


}
