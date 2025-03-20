package com.hiberus.hiring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.hiring.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

	Optional<Product> findByPartNumber(String partNumber);
}
