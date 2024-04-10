package com.backend.favoritesmanagementservice.controller;

import com.backend.favoritesmanagementservice.service.MessageSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMessageController {

    private final MessageSenderService senderService;

    @Autowired
    public SendMessageController(MessageSenderService senderService) {
        this.senderService = senderService;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        senderService.sendMessage(message);
        return "Message sent: " + message;
    }
}

