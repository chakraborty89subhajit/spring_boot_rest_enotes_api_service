package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.FavoriteNote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavoriteNoteRepository  extends JpaRepository<FavoriteNote,Integer> {

    List<FavoriteNote> findByUserId(int userId);
}
