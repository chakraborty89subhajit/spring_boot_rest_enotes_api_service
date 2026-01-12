package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.dto.CategoryResponse;
import com.example.enotes_api_service.entity.Category;
import com.example.enotes_api_service.exception.ResourceNotFoundException;
import com.example.enotes_api_service.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
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


@GetMapping("/{id}")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id
    )
//{
        throws Exception{
        /**

        CategoryDTO categoryDTO = categoryService.getCategoryById(id);
        if(ObjectUtils.isEmpty(categoryDTO)){
            return new ResponseEntity<>("category not found with id :"+id,
                    HttpStatus.NOT_FOUND);

        }else{
            return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
        }
**/
        //in order to activate the globalExceptionHandler we need to use throws keyword not try-catch
       //try{
            CategoryDTO categoryDTO = categoryService.getCategoryById(id);
            if(ObjectUtils.isEmpty(categoryDTO)){
                return new ResponseEntity<>("category not found with id :"+id,HttpStatus.NOT_FOUND);

            }else{
                return new ResponseEntity<>(categoryDTO,HttpStatus.OK);
            }


       // }
        /**
         // handles the custom exception
        catch(ResourceNotFoundException e){
            log.error("controller:getCategoryDetailsById::"+e.getMessage());
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
         **/

        //generic exception handle
        /**
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
         **/
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id){
        Boolean deleted = categoryService.deleteCategory(id);
         if(deleted){
             return new ResponseEntity<>("category deleted successfully",HttpStatus.OK);
         }else{
             return new ResponseEntity<>("category not deleted",
                     HttpStatus.INTERNAL_SERVER_ERROR);
         }

    }

@GetMapping("/hello")
    public ResponseEntity<?> hello(){
        String name =  null;
        name.toUpperCase();
        return new ResponseEntity<>(name,HttpStatus.OK);
    }

}
