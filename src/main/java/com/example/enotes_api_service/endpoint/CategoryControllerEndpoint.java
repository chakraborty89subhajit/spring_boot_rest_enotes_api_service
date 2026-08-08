package com.example.enotes_api_service.endpoint;

import com.example.enotes_api_service.dto.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/v1/category")
public interface CategoryControllerEndpoint {

    @PostMapping("/save-category")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> savecategory(@Valid @RequestBody CategoryDTO categoryDTO);

    @GetMapping("/categories")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> getAllCategory();

    @GetMapping("/active-category")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<?> getActiveCategory();

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getCategoryDetailsById(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer id);

}