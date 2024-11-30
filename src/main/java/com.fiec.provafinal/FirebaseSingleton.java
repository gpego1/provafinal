package com.fiec.provafinal;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;

public class FirebaseSingleton {

    private static FirebaseSingleton instance = null;

    private FirebaseSingleton(){
        try {
            String filePath = System.getenv("HOMEPATH") + "/Downloads/fiec2024-projeto.json";
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(
                            new FileInputStream(filePath)))
                    .build();
            FirebaseApp.initializeApp(options);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public static FirebaseSingleton getInstance(){
        if (instance == null) {
            instance = new FirebaseSingleton();
        }
        return instance;
    }
}