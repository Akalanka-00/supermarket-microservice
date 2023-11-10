package com.flashmart.auth.UserController;

import com.flashmart.auth.UserDTO.otpObject;
import com.flashmart.auth.UserDTO.resendOTPrequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.flashmart.auth.Entity.customer;
import com.flashmart.auth.Service.otpservice;


import java.util.List;

@RestController
@RequestMapping("/users")
public class otpcontroller {

    private final otpservice Otpservice;

    @Autowired
    public otpcontroller (otpservice Otpservice){
        this.Otpservice =Otpservice;
    }

    @PostMapping("/generate-otp")
    public void generateAndSaveOTP(@RequestBody customer customer) {
        Otpservice.generateAndSaveOTP(customer);
        // Return a success response or handle errors accordingly
    }

    @PostMapping("/verify-otp")
    public boolean verifyOTP(@RequestBody otpObject otpData) {
        System.out.println(otpData.getOtp());
        System.out.println(otpData.getCus_email());
        return Otpservice.verifyOTP(otpData.getCus_email(), otpData.getOtp());
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<String> resendOTP(@RequestBody resendOTPrequest resendOTPrequest) {
        try {
            Otpservice.resendOTP(resendOTPrequest.getEmail());
            return ResponseEntity.ok("OTP resent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to resend OTP: " + e.getMessage());
        }
    }
}
