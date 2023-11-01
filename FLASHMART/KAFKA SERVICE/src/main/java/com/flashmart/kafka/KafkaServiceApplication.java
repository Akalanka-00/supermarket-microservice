package com.flashmart.kafka;

import com.flashmart.kafka.consts.KAFKA_HEADERS;
import com.flashmart.kafka.consts.NOTIFICATION_TYPE;
import com.flashmart.kafka.event.NotificationBuilder;
import com.flashmart.kafka.firebase.FirestoreCollection;
import com.flashmart.kafka.mapper.NotificationMapper;
import com.flashmart.kafka.service.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

import java.io.IOException;

@SpringBootApplication
@Slf4j
public class KafkaServiceApplication {

    @Autowired
    private NotificationService notificationService;

    private final NotificationMapper mapper = new NotificationMapper();
    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);
    }


    @KafkaListener(topics = KAFKA_HEADERS.NOTIFICATION)
    public void handleNotifications(NotificationBuilder notificationBuilder) throws IOException {
        if(notificationBuilder.getType()== NOTIFICATION_TYPE.BROADCAST)
       notificationService.handleNotification(mapper.notificationToBroadcast(notificationBuilder));

        if(notificationBuilder.getType()== NOTIFICATION_TYPE.USER_DRIVEN)
            notificationService.handleNotification(mapper.notificationToUser(notificationBuilder));
    }


}
