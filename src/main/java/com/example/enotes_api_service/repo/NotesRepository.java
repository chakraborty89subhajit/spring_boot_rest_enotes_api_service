package com.example.enotes_api_service.repo;

import com.example.enotes_api_service.entity.Notes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotesRepository  extends JpaRepository<Notes, Integer> {
}
