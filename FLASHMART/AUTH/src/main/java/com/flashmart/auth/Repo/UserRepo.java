package com.flashmart.auth.Repo;

import com.flashmart.auth.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface UserRepo extends JpaRepository <User, Integer> {
    User findByEmail(String email);

    Optional<User> findOneByEmailAndPassword(String email, String encodedPassword);

    List<User> findByType(int type);

}
