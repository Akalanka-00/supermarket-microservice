package com.flashmart.order.repository;

import com.flashmart.order.model.orderModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<orderModel, Long> {
    @Query("SELECT o FROM orderModel o WHERE o.cusid = :customerId")
    List<orderModel> findByCustomerId(String customerId);
}
