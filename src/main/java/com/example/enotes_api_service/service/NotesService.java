package com.example.enotes_api_service.service;

import com.example.enotes_api_service.dto.FavoriteNoteDTO;
import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.dto.NotesResponse;
import com.example.enotes_api_service.entity.FileDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface NotesService  {
    public Boolean saveNotes(String notes, MultipartFile file) throws Exception;
    public List<NotesDTO> getAllNotes();

    public byte[] downloadFile(FileDetails fileDetails) throws Exception;
    public FileDetails getFileDetails(Integer id) throws Exception;
    public NotesResponse getAllNotesByUser(Integer userId,Integer pageNo,Integer pageSize);

    public void softDeleteNotes(Integer id) throws Exception;

    public void restoreNote(Integer id) throws Exception;

    public List<NotesDTO> getUserRecycleBinNotes(Integer userId);

    public void hardDeleteNotes(Integer id) ;

    public  void emptyRecycleBin(int userId);


    //favorite notes methods
    public void favoriteNotes(Integer id) throws Exception;
    public void unfavoriteNotes(Integer id) throws Exception;
    public List<FavoriteNoteDTO> getuserFavoriteNotes() throws Exception;
}
