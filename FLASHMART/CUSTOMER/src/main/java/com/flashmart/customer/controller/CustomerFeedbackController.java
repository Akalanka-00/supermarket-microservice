package com.flashmart.customer.controller;

import com.flashmart.customer.dto.CartDTO;
import com.flashmart.customer.dto.FeedbackDTO;
import com.flashmart.customer.dto.ItemDTO;
import com.flashmart.customer.model.Feedback;
import com.flashmart.customer.service.CustomerCartService;
import com.flashmart.customer.service.CustomerFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("api/v1/feedback")
public class CustomerFeedbackController {

    @Autowired
    private CustomerFeedbackService customerFeedbackService;

    // get all feedbacks
    @GetMapping("/getFeedback")
    public List<Feedback> getAllFeedbacks(){
        return customerFeedbackService.getAllFeedbacks();
    }

    // get feedbacks by refNo
    @GetMapping("/getFeedback/{refNo}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable Long refNo){
        return customerFeedbackService.getFeedbackById(refNo);
    }

    // get feedbacks by customerId
    @GetMapping("/getCustomerFeedback/{customerId}")
    public List<Feedback> getFeedbackByCustomerId(@PathVariable Long customerId){
        return customerFeedbackService.getFeedbackByCustomerId(customerId);
    }

    // give new feedback
    @PostMapping("/giveFeedback/{customerId}")
    public Feedback createFeedback( @PathVariable Long customerId, @RequestBody FeedbackDTO feedbackDTO ) {
        return customerFeedbackService.createFeedback(customerId, feedbackDTO);
    }

    // Update an existing feedback
    @PutMapping("/updateFeedback/{refNo}")
    public Feedback updateFeedback( @PathVariable Long refNo, @RequestBody FeedbackDTO feedbackDTO ) {
        return customerFeedbackService.updateFeedback(refNo, feedbackDTO);
    }

    // Remove an existing feedback
    @DeleteMapping("/removeFeedback/{refNo}")
    public String removeFeedback( @PathVariable Long refNo ) {
        return customerFeedbackService.removeFeedback(refNo);
    }
}

