package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.NotesDTO;

import java.util.List;

public interface NotesService {
    public Boolean saveNotes(NotesDTO notesDTO);
    public List<NotesDTO> getAllNotes();

}
