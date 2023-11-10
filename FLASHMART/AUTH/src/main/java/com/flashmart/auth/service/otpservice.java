package com.flashmart.auth.Service;

import com.flashmart.auth.Repo.customerRepository;
import com.flashmart.auth.Entity.customer;
import com.flashmart.auth.exception.ResourceNotFoundException;
import com.flashmart.auth.Service.EmailService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.*;

@Service
public class otpservice {

    private static otpservice instance;

    private otpservice() {
        // Private constructor to prevent instantiation
    }

    public static synchronized otpservice getInstance() {
        if (instance == null) {
            instance = new otpservice();
        }
        return instance;
    }
    @Autowired
    private customerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    public void generateAndSaveOTP(customer customer) {
        // Generate a 5-digit OTP
        String otp = RandomStringUtils.randomNumeric(5);
        customer.setOtp(otp);
        customerRepository.save(customer);

        // Send the OTP to the user's email
        emailService.sendOTP(customer.getEmail(), otp);
    }

    public boolean verifyOTP(String email, String enteredOTP) {
        customer Customer = customerRepository.findByEmail(email);
        if (Customer == null) {
            // User not found
            return false;
        }

        String storedOTP = Customer.getOtp();

        // Compare the entered OTP with the stored OTP
        
        return enteredOTP.equals(storedOTP);
    }


    public void resendOTP(String email) throws ResourceNotFoundException {
        customer Customer = customerRepository.findByEmail(email);
        if (Customer == null) {
            throw new ResourceNotFoundException("User", "email", email);
        }

        // Erase the existing OTP
        Customer.setOtp(null);
        customerRepository.save(Customer);

        // Generate and send a new OTP
        generateAndSaveOTP(Customer);
    }

}
