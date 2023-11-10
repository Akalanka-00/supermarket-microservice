package com.flashmart.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserNotificationRequest {
    private String title;
    private String body;
    private String url;
    private Date pushedDate;
    private List<String> targetUsers;
}
