package com.flashmart.customer.repository;

import com.flashmart.customer.model.PointData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<PointData, Long> {
}
