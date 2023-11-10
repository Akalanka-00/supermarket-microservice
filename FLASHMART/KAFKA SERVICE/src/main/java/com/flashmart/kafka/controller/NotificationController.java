package com.flashmart.kafka.controller;


import com.flashmart.kafka.dto.NotificationResponse;
import com.flashmart.kafka.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/notification")
@RequiredArgsConstructor
public class NotificationController {

    @Autowired
    private NotificationService notificationService;
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}/{type}")
    public List<NotificationResponse> getNotifications(@PathVariable String id, @PathVariable int type) throws ExecutionException, InterruptedException {

        return notificationService.getNotifications(id, type);
    }
}
