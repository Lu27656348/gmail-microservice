package com.example.sppgmailmicroservice.controllers;

import com.example.sppgmailmicroservice.interfaces.requests.SendEmailRequest;
import com.example.sppgmailmicroservice.interfaces.responses.MessageResponse;
import com.example.sppgmailmicroservice.services.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

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
    public ResponseEntity<MessageResponse> sendEmailWithFile(@RequestParam("file") MultipartFile file, @RequestParam("emailData") String emailData) throws MessagingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SendEmailRequest emailDataRequest = (mapper.readValue(emailData, SendEmailRequest.class));
        return emailService.sendEmail(
                file,
                emailDataRequest.getEmailFrom(),
                emailDataRequest.getEmailTo(),
                emailDataRequest.getSubject(),
                emailDataRequest.getHtmlContent()
        );
    }

    @PostMapping("/send/multiple/file")
    public ResponseEntity<MessageResponse> sendEmailWithMultipleFile(@RequestParam("file") MultipartFile[] file, @RequestParam("emailData") String emailData) throws MessagingException, JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SendEmailRequest emailDataRequest = (mapper.readValue(emailData, SendEmailRequest.class));
        return emailService.sendMultipleEmail(
                file,
                emailDataRequest.getEmailFrom(),
                emailDataRequest.getEmailTo(),
                emailDataRequest.getSubject(),
                emailDataRequest.getHtmlContent()
        );
    }
}
