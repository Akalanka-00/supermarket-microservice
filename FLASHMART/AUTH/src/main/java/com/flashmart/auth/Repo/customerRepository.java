package com.flashmart.auth.Repo;

import com.flashmart.auth.Entity.customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface customerRepository extends JpaRepository<customer, Long> {
    customer findByEmail(String email);

}
