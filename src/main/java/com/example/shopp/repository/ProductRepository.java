package com.example.shopp.repository;

import com.example.shopp.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findAllByProductId(Long id);
    Optional<Product> findAllByProductName(String productName);
}
