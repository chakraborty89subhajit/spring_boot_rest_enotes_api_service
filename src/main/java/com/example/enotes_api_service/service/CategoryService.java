package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.CategoryResponse;
import com.example.enotes_api_service.entity.Category;

import java.util.List;

public interface CategoryService {
   // public Boolean saveCategory(Category category);
    public Boolean saveCategory(CategoryDTO categoryDTO);
    public List<CategoryDTO> getAllCategory();
    public List<CategoryResponse> getActiveCategory();
}
