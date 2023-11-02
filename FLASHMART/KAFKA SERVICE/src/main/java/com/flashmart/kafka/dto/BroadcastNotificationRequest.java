package com.flashmart.kafka.dto;

import com.flashmart.kafka.event.EmailBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BroadcastNotificationRequest {
    private String title;
    private String body;
    private String url;
    private Date pushedDate;
    private List<Integer> targetAudience;
}
