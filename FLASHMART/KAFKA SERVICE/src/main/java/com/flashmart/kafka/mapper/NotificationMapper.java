package com.flashmart.kafka.mapper;

import com.flashmart.kafka.dto.BroadcastNotificationRequest;
import com.flashmart.kafka.dto.UserNotificationRequest;
import com.flashmart.kafka.event.NotificationBuilder;
import org.springframework.context.annotation.Bean;


public class NotificationMapper {

    public BroadcastNotificationRequest notificationToBroadcast(NotificationBuilder notification){
        return new BroadcastNotificationRequest(notification.getTitle(), notification.getBody(), notification.getUrl(),notification.getTargetAudience());
    }

    public UserNotificationRequest notificationToUser(NotificationBuilder notification){
        return new UserNotificationRequest(notification.getTitle(), notification.getBody(), notification.getUrl(),notification.getTargetUsers());
    }
}
