package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ToDoRepository extends JpaRepository<ToDo,Integer> {

   // List<ToDo>  findByCreatedBy(Integer userId);
   @Query("SELECT t FROM ToDo t WHERE t.created_by = :createdBy")
   List<ToDo> findByCreatedBy(@Param("createdBy") Integer createdBy);
}
