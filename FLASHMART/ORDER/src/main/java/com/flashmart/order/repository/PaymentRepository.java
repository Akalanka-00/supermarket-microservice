package com.flashmart.order.repository;

import com.flashmart.order.model.payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<payment, Integer> {
}
