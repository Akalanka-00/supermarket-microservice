package com.flashmart.customer.repository;

import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<Feedback, Long> {
}
