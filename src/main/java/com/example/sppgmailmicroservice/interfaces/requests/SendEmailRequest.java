package com.example.sppgmailmicroservice.interfaces.requests;

import lombok.Data;

@Data
public class SendEmailRequest {
    private String emailTo;
    private String emailFrom;

}
