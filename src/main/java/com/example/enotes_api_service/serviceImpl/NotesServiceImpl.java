package com.example.enotes_api_service.serviceImpl;

import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.entity.Notes;
import com.example.enotes_api_service.repo.NotesRepository;
import com.example.enotes_api_service.service.NotesService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NotesServiceImpl implements NotesService {

    @Autowired
    private NotesRepository notesRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public Boolean saveNotes(NotesDTO notesDTO) {

        Notes notes = mapper.map(notesDTO,Notes.class);

        Notes saveNotes = notesRepository.save(notes);

        if(!ObjectUtils.isEmpty(saveNotes)){
            return true;
        }else{
            return false;
        }


    }

    @Override
    public List<NotesDTO> getAllNotes() {
        return notesRepository
                .findAll()
                .stream()
                .map(note-> mapper.map(note, NotesDTO.class))
                .collect(Collectors.toList());
    }
}
