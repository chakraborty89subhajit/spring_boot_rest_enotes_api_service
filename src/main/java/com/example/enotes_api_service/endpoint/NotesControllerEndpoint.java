package com.example.enotes_api_service.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import static com.example.enotes_api_service.util.Constant.ROLE_ADMIN;
import static com.example.enotes_api_service.util.Constant.ROLE_USER;
import static com.example.enotes_api_service.util.Constant.ROLE_ADMIN_USER;
import static com.example.enotes_api_service.util.Constant.DEFAULT_PAGE_NO;
import static com.example.enotes_api_service.util.Constant.DEFAULT_PAGE_SIZE;


@RequestMapping("/api/v1/notes")
public interface NotesControllerEndpoint {

    @PostMapping("/save-notes")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> saveNotes
            (@RequestParam String notes,
             @RequestParam (required = false) MultipartFile file) throws Exception;

    @GetMapping("/getallnotes")
    @PreAuthorize(ROLE_ADMIN)
    public ResponseEntity<?> getAllNotes();

    @GetMapping("/download/{id}")
    @PreAuthorize(ROLE_ADMIN_USER)
    public ResponseEntity<?> downloadFile(@PathVariable Integer id) throws Exception;

    @GetMapping("/user-notes")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> getAllNotesByUser(
            @RequestParam(name="pageNo",defaultValue = "0") Integer pageNo,
            @RequestParam(name="pageSize",defaultValue = "10") Integer pageSize
    );

    @GetMapping("/delete/{id}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> deleteNots(@PathVariable Integer id) throws Exception;

    @GetMapping("/restore/{id}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> restoreNotes(@PathVariable Integer id) throws Exception;

    @GetMapping("/recycle-bin")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> getUserRecycleBinNotes() throws Exception;

    @DeleteMapping("/delete/{id}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> hardDeleteNotes(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/delete")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> emptyRecycleBin() throws Exception;

    @GetMapping("/fav/{id}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> favoriteNote(@PathVariable Integer id) throws Exception;

    @DeleteMapping("/un-fav/{favNoteId}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> nFavoriteNote(@PathVariable Integer favNoteId)throws Exception;


    @GetMapping("/fav-note")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> getUserFavoriteNotes() throws Exception;

    @GetMapping("/copy/{id}")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> copyNotes(@PathVariable Integer id) throws Exception;

    @GetMapping("/search")
    @PreAuthorize(ROLE_USER)
    public ResponseEntity<?> searchNotes(@RequestParam(name="key",defaultValue = "") String key,
                                         @RequestParam(name="pageNo",defaultValue = DEFAULT_PAGE_NO) Integer pageNO,
                                         @RequestParam(name="pageSize",defaultValue = DEFAULT_PAGE_SIZE) Integer pageSize);



    }
