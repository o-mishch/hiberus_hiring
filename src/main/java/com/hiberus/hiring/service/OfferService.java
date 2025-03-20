package com.hiberus.hiring.service;

import java.util.List;

import com.hiberus.hiring.controller.dto.OfferByPartNumber;
import com.hiberus.hiring.controller.dto.OfferDto;

public interface OfferService {
	void createNewOffer(OfferDto offer);

	void deleteAllOffers();

	void deleteOfferById(Long id);

	List<OfferDto> getAllOffers();

	OfferDto getOfferById(Long offerId);

	List<OfferByPartNumber> getOfferByPartNumber(Integer brandId, String partNumber);
}
