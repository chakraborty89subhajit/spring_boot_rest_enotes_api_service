package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {


    Boolean existsByEmail(String email);
}
