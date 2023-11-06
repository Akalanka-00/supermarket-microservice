package com.flashmart.customer.repository;

import com.flashmart.customer.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerItemRepository extends JpaRepository<Item, Long> {
}
