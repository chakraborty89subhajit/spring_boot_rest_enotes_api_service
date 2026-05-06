package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,Integer> {

    List<ToDo>  findByCreated_by(Integer userId);
}
