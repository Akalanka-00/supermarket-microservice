package com.flashmart.customer.repository;

import com.flashmart.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCartRepository extends JpaRepository<Customer, Long> {
}
