package com.flashmart.auth.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SuperBuilder
public class resendOTPrequest {
    private String email;

}