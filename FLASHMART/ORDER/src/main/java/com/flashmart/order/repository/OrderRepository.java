package com.flashmart.order.repository;

import com.flashmart.order.model.orderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<orderModel, Long> {
}
