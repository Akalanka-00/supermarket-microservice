package com.flashmart.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class NotificationModel {


    private String title;
    private String body;
    private String url;
    private Date pushedDate;
    private List<Integer> targetAudience;
    private List<String> targetUsers;
}
