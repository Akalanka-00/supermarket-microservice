package com.flashmart.delivery.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailBuilder {
    private String email;
    private String subject;
    private String body;
}
