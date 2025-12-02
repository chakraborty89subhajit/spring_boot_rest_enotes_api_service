package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.entity.Category;
import com.example.enotes_api_service.repo.CategoryRepository;
import com.example.enotes_api_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;



    @Override
    public Boolean saveCategory(Category category) {
        category.setIs_deleted(false);
        category.setCreated_by(1);
        category.setCreated_on(new Date());

        Category saveCategory = categoryRepository.save(category);
        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }

    @Override
    public List<Category> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }
}
