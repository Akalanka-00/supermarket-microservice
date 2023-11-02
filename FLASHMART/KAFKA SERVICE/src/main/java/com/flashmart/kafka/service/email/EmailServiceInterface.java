package com.flashmart.kafka.service.email;

public interface EmailServiceInterface {

    void sendEmail(String email, String subject, String body);
}
