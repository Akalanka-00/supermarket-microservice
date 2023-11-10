package com.flashmart.auth.UserDTO;

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
