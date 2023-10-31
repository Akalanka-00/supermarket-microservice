package com.flashmart.kafka;

import com.flashmart.kafka.consts.KAFKA_HEADERS;
import com.flashmart.kafka.event.NotificationBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class KafkaServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaServiceApplication.class, args);
    }

    @KafkaListener(topics = KAFKA_HEADERS.NOTIFICATION)
    public void handleNotifications(NotificationBuilder notificationBuilder){
        log.info("Recieved notification for\t{}",notificationBuilder.getTitle() );
    }
}
