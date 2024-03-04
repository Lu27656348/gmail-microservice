package com.example.sppgmailmicroservice.controllers;

import com.example.sppgmailmicroservice.interfaces.requests.SendEmailRequest;
import com.example.sppgmailmicroservice.interfaces.responses.MessageResponse;
import com.example.sppgmailmicroservice.services.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@RestController
@RequestMapping("email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<MessageResponse> sendEmail(@RequestBody SendEmailRequest sendEmailRequest) throws MessagingException {
        return emailService.sendNotificationEmail(sendEmailRequest);
    }

    @PostMapping("/send/file")
    public ResponseEntity<MessageResponse> sendEmailWithFile(@RequestParam("file") MultipartFile file, @RequestParam("emailFrom") String emailFrom, @RequestParam("emailTo") String emailTo) throws MessagingException {
        return emailService.sendEmail(file,emailFrom,emailTo);
    }

    @PostMapping("/send/multiple/file")
    public ResponseEntity<MessageResponse> sendEmailWithMultipleFile(@RequestParam("file") MultipartFile[] file, @RequestParam("emailFrom") String emailFrom, @RequestParam("emailTo") String emailTo) throws MessagingException {
        return emailService.sendMultipleEmail(file,emailFrom,emailTo);
    }
}
