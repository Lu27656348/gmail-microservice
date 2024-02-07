package com.example.sppgmailmicroservice.services;

import com.example.sppgmailmicroservice.interfaces.responses.MessageResponse;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public ResponseEntity<MessageResponse> sendEmail(MultipartFile file[], String emailFrom, String emailTo) throws MessagingException {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
            mimeMessageHelper.setFrom(emailFrom);
            mimeMessageHelper.setTo(emailTo);
            mimeMessageHelper.setSubject("Prueba");
            mimeMessageHelper.setText("Mensaje de prueba");
            if(file != null) {
                for (int i = 0; i < file.length; i++) {
                    try (InputStream is = file[i].getInputStream()) {
                            File fileTemp = File.createTempFile("upload", ".tmp");
                            file[i].transferTo(fileTemp);
                            mimeMessageHelper.addAttachment(
                                    mimeMessage.getFileName(), fileTemp
                            );
                    } catch (IOException e) {
                        throw new IOException(e.getMessage());
                    }
                }
            }

            javaMailSender.send(mimeMessage);
            return ResponseEntity.ok(new MessageResponse("Correo enviado exitosamente"));
        }catch (Exception e){
            throw  new RuntimeException(e);
        }


    }
}
