package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {


    Boolean existsByEmail(String email);


    //find user by there email
    User findByEmail(String email);
}
