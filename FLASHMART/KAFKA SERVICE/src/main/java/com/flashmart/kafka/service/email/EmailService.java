package com.flashmart.kafka.service.email;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

//    @Value("${spring.mail.username}")
    private final String fromMail="shenalakalanka513@gmail.com";

    @Autowired
    private  JavaMailSender mailSender;

    public void sendEmail(String email, String subject, String body){

        try {
         SimpleMailMessage mail = new SimpleMailMessage();
         mail.setFrom(fromMail);
         mail.setSubject(subject);
         mail.setText(body);
         mail.setTo(email);
         mailSender.send(mail);

         log.info("Email has been sent!!!");

     }catch (Exception e){
         log.info("Error occurred "+e.getMessage());
         log.info(fromMail);
         log.info(e.toString());
     }
    }


}
