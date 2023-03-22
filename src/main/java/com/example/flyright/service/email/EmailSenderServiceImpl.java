package com.example.flyright.service.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailSenderServiceImpl implements EmailSenderService {

 @Autowired
 JavaMailSender javaMailSender;

    @Async
    @Override
    public void send(String toEmail, String body) {
        try{
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage,"utf-8");
            message.setTo(toEmail);
            message.setSubject("Confirm Your Email");
            message.setText(body,true);
            message.setFrom("obusederek@gmail.com");
            javaMailSender.send(mimeMessage);


        } catch (MessagingException | MailException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

    }
}
