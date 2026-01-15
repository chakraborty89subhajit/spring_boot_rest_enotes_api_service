package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.CategoryResponse;
import com.example.enotes_api_service.entity.Category;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.repo.CategoryRepository;
import com.example.enotes_api_service.service.CategoryService;
import com.example.enotes_api_service.util.Validation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
@Autowired
private CategoryRepository categoryRepository;
@Autowired
private ModelMapper mapper;
@Autowired
private Validation validation;




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
       /** Category category = mapper.map(categoryDTO,Category.class);
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
        **/
       //implementing update in one api



        Category category = mapper.map(categoryDTO, Category.class);

        // ensure isActive is NOT NULL
        if (category.getIsActive() == null) {
            category.setIsActive(true);
        }
        // Check if ID exists → update
        if (category.getId() != null) {
            category = updateCategory(category); // <-- use updateCategory method
        } else {

            category.setIsDeleted(false);
            category.setCreated_by(1);
            category.setCreated_on(new Date());
        }
        Category saveCategory = categoryRepository.save(category);

        return !ObjectUtils.isEmpty(saveCategory);
        }





        //updateCategory
private Category updateCategory(Category category){
Optional<Category> findById = categoryRepository.findById(category.getId());
if(findById.isPresent()){
    Category existCategory = findById.get();
    category.setCreated_by(existCategory.getCreated_by());
    category.setCreated_on(existCategory.getCreated_on());
    category.setIsDeleted(existCategory.getIsDeleted());
    category.setUpdated_by(1);
    category.setUpdatedOn(new Date());
    return category;
}
return category;
}

    @Override
    public List<CategoryDTO> getAllCategory() {
       // List<Category> categories = categoryRepository.findAll();
       // List<Category> categories = categoryRepository.findByIsDeletedFalse();
        List<Category> categories = categoryRepository.findByIsActiveTrueAndIsDeletedFalse();
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

    @Override
    public CategoryDTO getCategoryById(Integer id) throws Exception{
    /**


      //  Optional<Category> findByCategory = categoryRepository.findById(id);
        Optional<Category> findByCategory = categoryRepository.findByIdAndIsDeletedFalse(id);
        if(findByCategory.isPresent()){
            Category category = findByCategory.get();
            return mapper.map(category,CategoryDTO.class);
        }else{
            return null;
        }

     **/
     Category category = categoryRepository.findByIdAndIsDeletedFalse(id)
             .orElseThrow(()->new ResourceNotFoundException("category not found with id : "+id ));
     if(!ObjectUtils.isEmpty(category)){
       //  if(category.getName()==null){
       //      throw new IlligalArgumentException("name is null");
       //  }
         return mapper.map(category,CategoryDTO.class);

     }else{
         return null;
     }


    }

    @Override
    public Boolean deleteCategory(Integer id) {
        Optional<Category> findByCategory = categoryRepository.findById(id);
        if(findByCategory.isPresent()){
            Category category = findByCategory.get();
            category.setIsDeleted(true);
            categoryRepository.save(category);
            return true;

        }else{
            return false;
        }

    }

    @Override
    public Boolean saveCategoryValiidation(CategoryDTO categoryDTO) {
        validation.categoryValidation(categoryDTO);
        Category category = mapper.map(categoryDTO,Category.class);
        if(ObjectUtils.isEmpty(category.getId())){
            category.setIsDeleted(false);
            category.setCreated_by(1);
            category.setCreated_on(new Date());

        }else{
            updateCategory(category);

        }
        Category saveCategory = categoryRepository.save(category);
        if(ObjectUtils.isEmpty(saveCategory)){
            return false;
        }
        return true;
    }


}
