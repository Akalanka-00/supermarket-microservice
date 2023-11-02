package com.flashmart.kafka.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
public class FirebaseService {

    private static FirebaseService instance = null;
    private static Firestore db;

    private FirebaseService() {
        // Exists only to defeat instantiation.
    }


    public static FirebaseService getInstance() {

        if(instance == null) {

            try {
                // Use a service account
                Resource resource = new ClassPathResource("serviceAccount.json");
                String path = resource.getFile().getAbsolutePath();
                InputStream serviceAccount = new FileInputStream(path);
                GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(credentials)
                        .build();
                FirebaseApp.initializeApp(options);
                db = FirestoreClient.getFirestore();
                instance = new FirebaseService();
            }catch (Exception e){
                log.info("Firebase Error occurred { }",e);
            }

        }
        return instance;
    }

    public Firestore db(){
        if(db==null){
            FirebaseService.getInstance();
        }
        return db;
    }

}