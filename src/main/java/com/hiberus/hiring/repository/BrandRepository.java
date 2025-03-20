package com.hiberus.hiring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.hiring.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Long> {
}
