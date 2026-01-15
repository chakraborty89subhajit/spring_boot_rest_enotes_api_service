package com.example.enotes_api_service.util;

import com.example.enotes_api_service.dto.CategoryDTO;
import com.example.enotes_api_service.exception.ValidationException;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Validation {

    public void categoryValidation(CategoryDTO categoryDTO) {

        Map<String, Object> error = new LinkedHashMap<>();

        if (ObjectUtils.isEmpty(categoryDTO)) {
            throw new IllegalArgumentException("Category object must not be null");
        }

        // name
        if (ObjectUtils.isEmpty(categoryDTO.getName())) {
            error.put("name", "name field is empty");
        } else {
            if (categoryDTO.getName().length() < 10) {
                error.put("name", "name length must be >= 10");
            }
            if (categoryDTO.getName().length() > 100) {
                error.put("name", "name length must be <= 100");
            }
        }

        // description
        if (ObjectUtils.isEmpty(categoryDTO.getDescription())) {
            error.put("description", "description field is empty");
        } else {
            if (categoryDTO.getDescription().length() < 10) {
                error.put("description", "description length must be >= 10");
            }
            if (categoryDTO.getDescription().length() > 100) {
                error.put("description", "description length must be <= 100");
            }
        }

        // is_active
        if (categoryDTO.getIs_active() == null) {
            error.put("is_active", "is_active must not be null");
        }

        if (!error.isEmpty()) {
            throw new ValidationException(error);
        }
    }
}
