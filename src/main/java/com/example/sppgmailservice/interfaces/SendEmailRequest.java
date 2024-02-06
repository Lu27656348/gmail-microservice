package com.example.sppgmailservice.interfaces;


import lombok.Data;

@Data
public class SendEmailRequest {
    private String fromEmailAddress;
    private String toEmailAddress;
}
