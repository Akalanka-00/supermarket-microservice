package com.flashmart.auth.dto;


import lombok.*;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@SuperBuilder
public class otpObject {
    private String cus_email;
    private String otp;
}
