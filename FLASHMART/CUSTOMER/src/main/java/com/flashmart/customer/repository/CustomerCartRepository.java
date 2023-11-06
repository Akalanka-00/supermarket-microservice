package com.flashmart.customer.repository;

import com.flashmart.customer.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends JpaRepository<Cart, Long> {
    @Query("SELECT SUM(c.totalPrice) FROM Cart c WHERE c.customer.customerId = :customerId")
    Double findTotalPriceByCustomerId(@Param("customerId") Long customerId);
}
