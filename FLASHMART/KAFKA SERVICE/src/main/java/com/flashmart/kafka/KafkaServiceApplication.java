package com.flashmart.kafka;

import com.flashmart.kafka.consts.KAFKA_HEADERS;
import com.flashmart.kafka.consts.NOTIFICATION_TYPE;
import com.flashmart.kafka.event.NotificationBuilder;
import com.flashmart.kafka.firebase.FirestoreCollection;
import com.flashmart.kafka.mapper.NotificationMapper;
import com.flashmart.kafka.service.NotificationService;
import com.flashmart.kafka.service.email.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class KafkaServiceApplication {

    @Autowired
    private NotificationService notificationService;
    @Autowired
    EmailService emailService;
    private final NotificationMapper mapper = new NotificationMapper();
    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);

    }
@EventListener(ApplicationReadyEvent.class)
    public void sendEmail(){
        emailService.sendEmail("shenalakalanka513@gmail.com","hii","This is from the main class");
        log.info("Email sent");
}


    @KafkaListener(topics = KAFKA_HEADERS.NOTIFICATION)
    public void handleNotifications(NotificationBuilder notificationBuilder) throws IOException {
        if(notificationBuilder.getType()== NOTIFICATION_TYPE.BROADCAST)
       notificationService.handleNotification(mapper.notificationToBroadcast(notificationBuilder));

        if(notificationBuilder.getType()== NOTIFICATION_TYPE.USER_DRIVEN)
            notificationService.handleNotification(mapper.notificationToUser(notificationBuilder));

        if(notificationBuilder.getType()== NOTIFICATION_TYPE.EMAIL)
            notificationService.handleEmail(mapper.notificationToEmail(notificationBuilder));
    }


}
