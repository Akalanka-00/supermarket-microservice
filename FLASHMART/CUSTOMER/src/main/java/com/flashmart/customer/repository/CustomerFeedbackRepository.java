package com.flashmart.customer.repository;

import com.flashmart.customer.model.Cart;
import com.flashmart.customer.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerFeedbackRepository extends JpaRepository<Feedback, Long> {
    @Query("SELECT f FROM Feedback f WHERE f.customer.customerId = :customerId")
    List<Feedback> findFeedbacksByCustomerId(@Param("customerId") Long customerId);
}
