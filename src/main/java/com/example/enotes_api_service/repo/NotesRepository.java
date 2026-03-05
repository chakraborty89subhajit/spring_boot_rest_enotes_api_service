package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.Notes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NotesRepository extends JpaRepository<Notes, Integer> {


    @Query("SELECT n FROM Notes n WHERE n.created_by = :userId AND (n.isDeleted = false OR n.isDeleted IS NULL)")
    Page<Notes> findActiveNotesByUser(@Param("userId") Integer userId, Pageable pageable);

   @Query("SELECT n FROM Notes n WHERE n.created_by = :userId")
    Page<Notes> findByCreatedByUser(@Param("userId") Integer userId, Pageable pageable);

   // List<Notes> findByCreatedByAndIsDeletedTrue(Integer userId);
   @Query("SELECT n FROM Notes n WHERE n.created_by = :userId AND n.isDeleted = true")
   List<Notes> findDeletedNotesByUser(@Param("userId") Integer userId);

    List<Notes> findAllByIsDeletedAndDeleteOnBefore(boolean b, LocalDateTime cutofftime);

   // List<Notes> findByCreated_ByAndIsDeletedTrue(int userId);
  // List<Notes> findByCreated_byAndIsDeletedTrue(Integer created_by);
    //List<Notes> findByCreated_byAndIsDeletedTrue(Integer created_by);

    @Query("SELECT n FROM Notes n WHERE n.created_by = :userId AND n.isDeleted = true")
    List<Notes> findByCreated_byAndIsDeletedTrue(@Param("userId") Integer userId);
   // @Query("SELECT n FROM Notes n WHERE n.createdBy = :createdBy AND n.isDeleted = true")
    //List<Notes> getDeletedNotesByCreatedBy(@Param("createdBy") Integer createdBy);

//Page<Notes> findByCreated_ByUserAndIsDeletedFalse(Integer userId,Pageable pageable);
//@Query("SELECT n FROM Notes n WHERE n.created_by = :userId AND n.isDeleted = false")
//Page<Notes> findActiveNotesByUser(@Param("userId") Integer userId, Pageable pageable);
}
