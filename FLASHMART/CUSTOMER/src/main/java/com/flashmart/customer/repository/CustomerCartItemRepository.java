package com.flashmart.customer.repository;

import com.flashmart.customer.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartItemRepository extends JpaRepository<CartItem, Long> {
}
