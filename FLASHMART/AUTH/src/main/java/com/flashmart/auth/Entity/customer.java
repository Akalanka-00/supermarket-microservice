package com.flashmart.auth.Entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long otpid;
    private String cusid;
    private String email;
    private String otp;

}
