package com.example.sppgmailservice.controllers;

import com.example.sppgmailservice.interfaces.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public ResponseEntity<MessageResponse> welcomeMessage(){
        return ResponseEntity.ok(new MessageResponse("Welcome to Luis Somoza Gmail API"));
    }

    @GetMapping("secured")
    public ResponseEntity<MessageResponse> securedRoute(){
        return ResponseEntity.ok(new MessageResponse("Welcome to Luis Somoza Secured Gmail API"));

    }
}
