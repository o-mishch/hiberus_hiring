package com.hiberus.hiring.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hiberus.hiring.controller.dto.OfferByPartNumber;
import com.hiberus.hiring.controller.dto.OfferDto;
import com.hiberus.hiring.service.OfferService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * You can change this controller but please do not change ends points signatures & payloads.
 */
@RestController
@RequiredArgsConstructor
public class OfferController {

	private final OfferService offerService;

	@PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewOffer(@RequestBody @Valid OfferDto offer) {
		offerService.createNewOffer(offer);
	}

	@DeleteMapping(value = "/offer")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOffers() {
		offerService.deleteAllOffers();
	}

	@DeleteMapping(value = "/offer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteOfferById(@PathVariable Long id) {
		offerService.deleteOfferById(id);
	}

	@GetMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferDto> getAllOffers() {
		return offerService.getAllOffers();
	}

	@GetMapping(value = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public OfferDto getOfferById(@PathVariable(value = "id") Long offerId) {
		return offerService.getOfferById(offerId);
	}

	@GetMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferByPartNumber> getOfferByPartNumber(@PathVariable Integer brandId, @PathVariable("partnumber") String partNumber) {
		return offerService.getOfferByPartNumber(brandId, partNumber);
	}
}
