package com.flashmart.kafka.service;

import com.flashmart.kafka.dto.BroadcastNotificationRequest;
import com.flashmart.kafka.dto.EmailRequest;
import com.flashmart.kafka.dto.NotificationResponse;
import com.flashmart.kafka.dto.UserNotificationRequest;
import com.flashmart.kafka.firebase.FirestoreCollection;
import com.flashmart.kafka.mapper.NotificationMapper;
import com.flashmart.kafka.model.NotificationModel;
import com.flashmart.kafka.service.email.EmailService;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificationService {

    FirestoreCollection firestoreCollection ;
    private final EmailService emailService = new EmailService();
    private final NotificationMapper mapper = new NotificationMapper();


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

    public void handleEmail(EmailRequest request){
        try {
            emailService.sendEmail("shenalakalanka513@gmail.com","hii","This is from the notification class");
            log.info("Email sent");
           // emailService.sendEmail(request.getEmail(),request.getSubject(),request.getBody());
        }catch (Exception exception){
            log.info(exception.getMessage());
        }
    }

    public List<NotificationResponse> getNotifications (String userId, int userType) throws ExecutionException, InterruptedException {
        firestoreCollection = FirestoreCollection.getInstance();
        Query q1 = firestoreCollection.getNotificationCollection().whereArrayContains("targetAudience", userType);
        Query q2 = firestoreCollection.getNotificationCollection().whereArrayContains("targetUsers", userId);

        List<QueryDocumentSnapshot> userTypeRes = q1.get().get().getDocuments();
        List<QueryDocumentSnapshot> userRes = q2.get().get().getDocuments();

        List<NotificationResponse> notificationList = new ArrayList<>(userTypeRes.stream()
                .map(doc -> {
                    NotificationResponse notificationResponse = doc.toObject(NotificationResponse.class);
                    notificationResponse.setId(doc.getId());
                    return notificationResponse;
                })
                .toList());
        notificationList.addAll(userRes.stream()
                .map(doc-> {
                    NotificationResponse notificationResponse = doc.toObject(NotificationResponse.class);
                    notificationResponse.setId(doc.getId());
                    return notificationResponse;
                })
                .toList());
return notificationList;
    }
}
