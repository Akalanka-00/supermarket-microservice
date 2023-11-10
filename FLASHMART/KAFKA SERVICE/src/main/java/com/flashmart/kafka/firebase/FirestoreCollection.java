package com.flashmart.kafka.firebase;

import com.google.cloud.firestore.CollectionReference;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FirestoreCollection {

    private static FirestoreCollection instance = null;
    private static final FirebaseService firebaseService = FirebaseService.getInstance();
    private static CollectionReference notificationRef;

    // Private constructor to prevent external instantiation
    private FirestoreCollection() {
        try {
            notificationRef = firebaseService.db().collection("NotificationCollection");
        }catch (NullPointerException e){
            log.info("Null value at collection");
        }
    }

    public static FirestoreCollection getInstance() {
        if (instance == null) {
            instance = new FirestoreCollection();
        }
        return instance;
    }

    public CollectionReference getNotificationCollection() {
        return notificationRef;
    }
}
