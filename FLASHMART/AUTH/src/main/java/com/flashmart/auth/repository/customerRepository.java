package com.flashmart.auth.repository;
import com.flashmart.auth.model.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<customer, Long> {
    customer findByEmail(String email);

}
