package com.hiberus.hiring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hiberus.hiring.entity.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {

	@Query("SELECT o FROM Offer o WHERE o.brand.id = :brandId AND o.product.partNumber = :partNumber")
	List<Offer> findByBrandIdAndProductPartNumber(@Param("brandId") Integer brandId, @Param("partNumber") String partNumber);
}
