package com.flashmart.kafka.mapper;

import com.flashmart.kafka.dto.BroadcastNotificationRequest;
import com.flashmart.kafka.dto.EmailRequest;
import com.flashmart.kafka.dto.UserNotificationRequest;
import com.flashmart.kafka.event.NotificationBuilder;


public class EmailMapper {

    public EmailRequest notificationToBroadcast(NotificationBuilder notification){
        return new EmailRequest();
    }


}
