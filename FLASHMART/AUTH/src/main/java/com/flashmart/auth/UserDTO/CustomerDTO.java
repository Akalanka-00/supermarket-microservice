package com.flashmart.auth.UserDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    private int userid;
    private String userfname;
    private String userlname;
    private String mobile;
    private String email;
}
