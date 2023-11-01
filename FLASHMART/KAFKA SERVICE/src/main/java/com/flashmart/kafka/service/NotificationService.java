package com.flashmart.kafka.service;

import com.flashmart.kafka.dto.BroadcastNotificationRequest;
import com.flashmart.kafka.dto.UserNotificationRequest;
import com.flashmart.kafka.firebase.FirestoreCollection;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class NotificationService {

    FirestoreCollection firestoreCollection ;

    public void handleNotification(BroadcastNotificationRequest request){
        try {
            firestoreCollection = FirestoreCollection.getInstance();
            firestoreCollection.getNotificationCollection().add(request);
        }catch (Exception exception){
            log.info(exception.getMessage());
        }
    }

    public void handleNotification(UserNotificationRequest request){
        try {
            firestoreCollection = FirestoreCollection.getInstance();
            firestoreCollection.getNotificationCollection().add(request);
        }catch (Exception exception){
            log.info(exception.getMessage());
        }
    }
}
