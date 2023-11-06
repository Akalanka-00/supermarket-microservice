package com.flashmart.customer.repository;

import com.flashmart.customer.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends JpaRepository<Cart, Long> {
}
