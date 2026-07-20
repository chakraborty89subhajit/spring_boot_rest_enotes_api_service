package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role,Integer> {
}
