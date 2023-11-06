package com.flashmart.customer.service;

import com.flashmart.customer.dto.FeedbackDTO;
import com.flashmart.customer.exception.ResourceNotFoundException;
import com.flashmart.customer.model.Customer;
import com.flashmart.customer.model.Feedback;
import com.flashmart.customer.repository.CustomerFeedbackRepository;
import com.flashmart.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerFeedbackService {

    @Autowired
    private final CustomerFeedbackRepository customerFeedbackRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    public CustomerFeedbackService(CustomerFeedbackRepository customerFeedbackRepository, CustomerRepository customerRepository) {
        this.customerFeedbackRepository = customerFeedbackRepository;
        this.customerRepository = customerRepository;
    }

    public List<Feedback> getAllFeedbacks(){
        List<Feedback> feedbacks = customerFeedbackRepository.findAll();
        return feedbacks;
    }

    public ResponseEntity<Feedback> getFeedbackById(Long refNo) {
        Feedback feedback = customerFeedbackRepository.findById(refNo)
                .orElseThrow(() -> new ResourceNotFoundException("The Feedback does not exist with Ref No: " + refNo));
        return ResponseEntity.ok(feedback);
    }

    public List<Feedback> getFeedbackByCustomerId(Long customerId) {
        List<Feedback> feedbacks = customerFeedbackRepository.findFeedbacksByCustomerId(customerId);
        return feedbacks;
    }

    public Feedback createFeedback(Long customerId, FeedbackDTO feedbackDTO) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("The Customer does not exist with ID: " + customerId));;
        Feedback newFeedback = new Feedback();
        newFeedback.setRating(feedbackDTO.getRating());
        newFeedback.setDescription(feedbackDTO.getDescription());
        newFeedback.setCustomer(customer);

        return customerFeedbackRepository.save(newFeedback);
    }

    public Feedback updateFeedback(Long refNo, FeedbackDTO feedbackDTO) {
        Feedback existingFeedback = customerFeedbackRepository.findById(refNo)
                .orElseThrow(() -> new ResourceNotFoundException("The Feedback does not exist with Ref No: " + refNo));;

        if (feedbackDTO.getRating() >= 1 && feedbackDTO.getRating() <= 5) {
            existingFeedback.setRating(feedbackDTO.getRating());
        }
        if (feedbackDTO.getDescription() != null) {
            existingFeedback.setDescription(feedbackDTO.getDescription());
        }

        return customerFeedbackRepository.save(existingFeedback);
    }

    public String removeFeedback(Long refNo) {
        Feedback existingFeedback = customerFeedbackRepository.findById(refNo)
                .orElseThrow(() -> new ResourceNotFoundException("The Feedback does not exist with Ref No: " + refNo));;
        customerFeedbackRepository.delete(existingFeedback);

        return "Your Feedback is Successfully Deleted!";
    }
}
