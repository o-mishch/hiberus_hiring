package com.hiberus.hiring.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hiberus.hiring.entity.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
	Optional<Currency> findByIsoCode(String currencyIso);
}
