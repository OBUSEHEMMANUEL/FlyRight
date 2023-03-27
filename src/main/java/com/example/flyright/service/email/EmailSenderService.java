package com.example.flyright.service.email;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {

   void send(String toEmail, String email);
}
