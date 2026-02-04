package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.service.NotesService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/notes")
public class NotesController {

    @Autowired
    private NotesService notesService;

    @PostMapping("/save-notes")
    public ResponseEntity<?> saveNotes
            (@RequestParam String notes,
             @RequestParam (required = false) MultipartFile file) throws Exception {
        Boolean saveNotes = notesService.saveNotes(notes,file);

        if(saveNotes){
            return CommonUtil.createBuildResponseMessage("notes save successfully",
                    HttpStatus.CREATED);
        }else{
           return CommonUtil.createErrorResponseMessage("notes not saved",
                   HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/getallnotes")
    public ResponseEntity<?> getAllNotes(){
        List <NotesDTO> notes = notesService.getAllNotes();

        if(CollectionUtils.isEmpty(notes)){
            return ResponseEntity.noContent().build();
        }else{
          return CommonUtil.createBuildResponse(notes,HttpStatus.OK);
        }
    }




}
