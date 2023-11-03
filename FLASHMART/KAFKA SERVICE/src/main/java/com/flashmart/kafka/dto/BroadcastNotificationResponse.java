package com.flashmart.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastNotificationResponse {
    private String id;
    private String title;
    private String body;
    private String url;
    private List<Integer> targetAudience;
}