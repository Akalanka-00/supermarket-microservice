package com.flashmart.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmailResponse {

    private String id;
    private String email;
    private String subject;
    private String body;
}
