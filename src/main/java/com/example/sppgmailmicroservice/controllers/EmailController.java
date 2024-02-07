package com.example.sppgmailmicroservice.controllers;

import com.example.sppgmailmicroservice.interfaces.requests.SendEmailRequest;
import com.example.sppgmailmicroservice.interfaces.responses.MessageResponse;
import com.example.sppgmailmicroservice.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendEmail(@RequestParam(value = "file", required = false)MultipartFile[] file, @RequestBody SendEmailRequest sendEmailRequest) throws MessagingException {
        return emailService.sendEmail(file,sendEmailRequest.getEmailFrom(),sendEmailRequest.getEmailTo());
    }
}
