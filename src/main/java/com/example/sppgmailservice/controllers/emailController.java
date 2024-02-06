package com.example.sppgmailservice.controllers;

import com.example.sppgmailservice.interfaces.MessageResponse;
import com.example.sppgmailservice.interfaces.SendEmailRequest;
import com.example.sppgmailservice.services.SendEmailService;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/email")
public class emailController {

    private final SendEmailService sendEmailService;

    public emailController(SendEmailService sendEmailService) {
        this.sendEmailService = sendEmailService;
    }

    @PostMapping
    public Message sendEmail(@RequestBody SendEmailRequest sendEmailRequest) throws MessagingException, GeneralSecurityException, IOException {
        return sendEmailService.sendEmail(sendEmailRequest.getFromEmailAddress(),sendEmailRequest.getToEmailAddress());
    }

    @PostMapping("/send/file")
    public ResponseEntity<MessageResponse> sendEmailFile(@RequestParam("file") MultipartFile file, @RequestParam("from") String fromEmail, @RequestParam("to") String toEmail) throws IOException, MessagingException, GeneralSecurityException {
        // Create a temporary file to store the MultipartFile's contents
        File tempFile = File.createTempFile("email_attachment", ".docx");

        // Transfer the MultipartFile's contents to the temporary file
        file.transferTo(tempFile);
        sendEmailService.createDraftMessageWithAttachment(fromEmail,toEmail, tempFile);
        return ResponseEntity.ok(new MessageResponse("Correo enviado exitosamente"));
    }
}
