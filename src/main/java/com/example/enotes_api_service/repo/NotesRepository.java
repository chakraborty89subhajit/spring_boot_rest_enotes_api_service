package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface NotesRepository extends JpaRepository<Notes, Integer> {


    @Query("SELECT n FROM Notes n WHERE n.created_by = :userId")
    Page<Notes> findByCreatedByUser(@Param("userId") Integer userId, Pageable pageable);
}
