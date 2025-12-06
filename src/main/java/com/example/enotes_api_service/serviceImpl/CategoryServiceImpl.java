package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.CategoryResponse;
import com.example.enotes_api_service.entity.Category;
import com.example.enotes_api_service.repo.CategoryRepository;
import com.example.enotes_api_service.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;
@Autowired
private ModelMapper mapper;




    //@Override
    //public Boolean saveCategory(Category category) {
    @Override
    public Boolean saveCategory(CategoryDTO categoryDTO){
      /**
        category.setIs_deleted(false);
        category.setCreated_by(1);
        category.setCreated_on(new Date());

        Category saveCategory = categoryRepository.save(category);
        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }
**/

      //===================by implementing DTO==================================
 /**
        Category category = new Category();
      category.setName(categoryDTO.getName());
      category.setDescription(categoryDTO.getDescription());
      category.setIs_active(categoryDTO.getIs_active());
      category.setIs_deleted(false);
      category.setCreated_by(1);
      category.setCreated_on(new Date());

      Category saveCategory = categoryRepository.save(category);
      if(ObjectUtils.isEmpty(saveCategory)){
          return false;

      }else{
        return true;

      }

    }
  **/
 //=====================by implementing modelmapper====================================
        Category category = mapper.map(categoryDTO,Category.class);
        category.setIsDeleted(false);
        category.setCreated_by(1);
        category.setCreated_on(new Date());
        Category saveCategory = categoryRepository.save(category);
        if(ObjectUtils.isEmpty(saveCategory)){
            return false;

        }else{
            return true;
        }

    }
    @Override
    public List<CategoryDTO> getAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList =
                categories
                        .stream()
                        .map(cat->mapper.map(cat,CategoryDTO.class))
                        .collect(Collectors.toList());
        return categoryDTOList;
    }

    @Override
    public List<CategoryResponse> getActiveCategory() {
        List<Category> categories = categoryRepository.findByIsActiveTrue();
        List<CategoryResponse> categoryList = categories
                .stream()
                .map(cat->mapper.map(cat,CategoryResponse.class))
                .collect(Collectors.toList());
        return categoryList;
    }


}
