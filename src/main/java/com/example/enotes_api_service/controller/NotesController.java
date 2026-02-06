package com.example.enotes_api_service.controller;

import com.example.enotes_api_service.dto.NotesDTO;
import com.example.enotes_api_service.dto.NotesResponse;
import com.example.enotes_api_service.entity.FileDetails;
import com.example.enotes_api_service.service.NotesService;
import com.example.enotes_api_service.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Paths;
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

    //implementing dowlod files with notes
    @GetMapping("/download/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception{

        FileDetails fileDetails = notesService.getFileDetails(id);
        byte[] data = notesService.downloadFile(fileDetails);
        HttpHeaders headers = new HttpHeaders();
        //String contentType = CommonUtil.getContentType(fileDetails.getOriginalFileName());
        String contentType = Files.probeContentType(Paths.get(fileDetails.getPath()));

        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        headers.setContentType(MediaType.parseMediaType(contentType));
        headers.setContentDispositionFormData("attachment",fileDetails.getOriginalFileName());


        return ResponseEntity.ok().headers(headers).body(data);
    }

    //getting all notes by any specific user
    @GetMapping("/user-notes")
    public ResponseEntity<?> getAllNotesByUser(
            @RequestParam(name="pageNo",defaultValue = "0") Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize
    ){
        //display only notes created by user id= 2
        Integer userId= 2;
        NotesResponse notes =notesService.getAllNotesByUser(userId,pageNo,pageSize);
        return CommonUtil.createBuildResponse(notes,HttpStatus.OK);
    }



}
