package com.flashmart.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailBuilder {
    private String email;
    private String subject;
    private String body;
    private Date pushedDate;
}
