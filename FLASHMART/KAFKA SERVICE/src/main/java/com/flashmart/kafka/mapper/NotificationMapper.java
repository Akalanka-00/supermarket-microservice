package com.flashmart.kafka.mapper;

import com.flashmart.kafka.dto.BroadcastNotificationRequest;
import com.flashmart.kafka.dto.EmailRequest;
import com.flashmart.kafka.dto.NotificationResponse;
import com.flashmart.kafka.dto.UserNotificationRequest;
import com.flashmart.kafka.event.EmailBuilder;
import com.flashmart.kafka.event.NotificationBuilder;
import com.flashmart.kafka.model.NotificationModel;
import org.springframework.context.annotation.Bean;

import java.util.Date;


public class NotificationMapper {

    public BroadcastNotificationRequest notificationToBroadcast(NotificationBuilder notification){
        return new BroadcastNotificationRequest(notification.getTitle(), notification.getBody(), notification.getUrl(), new Date(), notification.getTargetAudience());
    }

    public UserNotificationRequest notificationToUser(NotificationBuilder notification){
        return new UserNotificationRequest(notification.getTitle(), notification.getBody(), notification.getUrl(),new Date(), notification.getTargetUsers());
    }

    public EmailRequest notificationToEmail(NotificationBuilder notification){
        EmailBuilder email = notification.getEmail();
        return new EmailRequest(email.getEmail(), email.getSubject(), email.getBody());
    }

    public NotificationResponse notificationToResponse(NotificationModel model, String id){
        return new NotificationResponse(id, model.getTitle(), model.getBody(), model.getUrl(), model.getPushedDate());
    }
}
