package com.example.enotes_api_service.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/api/v1/notes")
public interface NotesControllerEndpoint {

    @PostMapping("/save-notes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> saveNotes
            (@RequestParam String notes,
             @RequestParam (required = false) MultipartFile file) throws Exception;

    @GetMapping("/getallnotes")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllNotes();

    @GetMapping("/download/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception;

    @GetMapping("/user-notes")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getAllNotesByUser(
            @RequestParam(name="pageNo",defaultValue = "0") Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize
    );

    @GetMapping("/delete/{id}")
    @PreAuthorize("haseRole('USER')")
    public ResponseEntity<?> deleteNots(@PathVariable Integer id) throws Exception;

    @GetMapping("/restore/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception;

    @GetMapping("/recycle-bin")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserRecycleBinNotes() throws Exception;

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("haseRole('USER')")
    public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> emptyRecycleBin() throws Exception;

    @GetMapping("/fav/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> favoriteNote(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/un-fav/{favNoteId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> nFavoriteNote(@PathVariable Integer favNoteId)throws Exception;


    @GetMapping("/fav-note")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserFavoriteNotes() throws Exception;

    @GetMapping("/copy/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception;

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> searchNotes(@RequestParam(name="key",defaultValue = "") String key,
                                         @RequestParam(name="pageNo",defaultValue = "0") Integer pageNO,
                                         @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize);



    }
