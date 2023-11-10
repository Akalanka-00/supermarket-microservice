package com.flashmart.order.repository;
import com.flashmart.order.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Custom query methods can be defined here if needed
}
