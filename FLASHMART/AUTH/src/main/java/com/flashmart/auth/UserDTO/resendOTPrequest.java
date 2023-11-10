package com.flashmart.auth.UserDTO;

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
