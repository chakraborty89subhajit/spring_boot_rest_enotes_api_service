package com.example.enotes_api_service.endpoint;

import com.example.enotes_api_service.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import static com.example.enotes_api_service.util.Constant.ROLE_ADMIN;
import static com.example.enotes_api_service.util.Constant.ROLE_USER;
import static com.example.enotes_api_service.util.Constant.ROLE_ADMIN_USER;


import javax.validation.Valid;

@RequestMapping("/api/v1/category")
public interface CategoryControllerEndpoint {

    @PostMapping("/save-category")
    @PreAuthorize(ROLE_ADMIN)
    public ResponseEntity<?> savecategory(@Valid @RequestBody CategoryDTO categoryDTO);

    @GetMapping("/categories")
    @PreAuthorize(ROLE_ADMIN_USER)
    public ResponseEntity<?> getAllCategory();

    @GetMapping("/active-category")
    @PreAuthorize(ROLE_ADMIN_USER)
    public ResponseEntity<?> getActiveCategory();

    @GetMapping("/{id}")
    @PreAuthorize(ROLE_ADMIN)
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/{id}")
    @PreAuthorize(ROLE_ADMIN)
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id);

}