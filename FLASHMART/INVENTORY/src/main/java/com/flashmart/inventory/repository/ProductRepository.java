package com.flashmart.inventory.repository;

import com.flashmart.inventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findBySkuCode(String skuCode);
}
