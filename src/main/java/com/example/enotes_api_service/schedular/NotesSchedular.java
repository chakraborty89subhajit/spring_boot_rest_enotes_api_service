package com.example.enotes_api_service.schedular;

import com.example.enotes_api_service.entity.Notes;
import com.example.enotes_api_service.repo.NotesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class NotesSchedular {

    @Autowired
    private NotesRepository notesRepository;

    @Scheduled(cron = "0 */2 * * * ?")
//@Scheduled(cron = "")
    public void deleteNoteSchedular(){
        LocalDateTime cutofftime = LocalDateTime.now().minusDays(7);
        List<Notes> deleteNotes = notesRepository.findAllByIsDeletedAndDeleteOnBefore(true,cutofftime);
        notesRepository.deleteAll(deleteNotes);
    }

}
