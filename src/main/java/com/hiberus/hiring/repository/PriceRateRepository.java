package com.hiberus.hiring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.hiring.entity.PriceRate;

public interface PriceRateRepository extends JpaRepository<PriceRate, Long> {
}
