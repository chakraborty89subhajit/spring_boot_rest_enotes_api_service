package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer>{
}
