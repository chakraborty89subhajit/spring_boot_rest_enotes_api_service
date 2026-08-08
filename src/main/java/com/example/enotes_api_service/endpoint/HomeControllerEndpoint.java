package com.example.enotes_api_service.endpoint;

import com.example.enotes_api_service.dto.PasswordResetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/api/v1/home")
public interface HomeControllerEndpoint {

    @GetMapping("/verify")
    public ResponseEntity<?> verifyUserAccount(@RequestParam Integer uid,
                                               @RequestParam String code ) throws Exception;

    @GetMapping("/send-email-reset")
    public ResponseEntity<?> sendEmailForPasswordreset(@RequestParam String email,
                                                       HttpServletRequest request)throws Exception;

    @GetMapping("/verify-password-link")
    public ResponseEntity<?> verifyPasswordResetLink(@RequestParam Integer uid,
                                                     @RequestParam String code) throws Exception;

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest
                                                   passwordResetRequest)throws Exception;

}
