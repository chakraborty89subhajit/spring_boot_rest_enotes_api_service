package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.entity.FileDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService  {
    public Boolean saveNotes(String notes, MultipartFile file) throws Exception;
    public List<NotesDTO> getAllNotes();

    public byte[] downloadFile(FileDetails fileDetails) throws Exception;
    public FileDetails getFileDetails(Integer id) throws Exception;


}
