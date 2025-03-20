package com.hiberus.hiring.controller;

import java.util.ArrayList;
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

import com.hiberus.hiring.controller.dto.Offer;
import com.hiberus.hiring.controller.dto.OfferByPartNumber;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

/**
 * You can change this controller but please do not change ends points signatures & payloads.
 */
@RestController
@Slf4j
public class OfferController {

	@PostMapping(value = "/offer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public void createNewOffer(@RequestBody @Valid Offer offer) {

		//TODO implement it!.

	}

	@DeleteMapping(value = "/offer")
	@ResponseStatus(HttpStatus.OK)
	public void deleteAllOffers() {

		//TODO implement it!.

	}

	@DeleteMapping(value = "/offer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteOfferById(@PathVariable Long id) {

		//TODO implement it!.

	}

	@GetMapping(value = "/offer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<Offer> getAllOffers() {

		//TODO implement it!.
		return new ArrayList<>();

	}

	@GetMapping(value = "/offer/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Offer getOfferById(@PathVariable(value = "id") Long offerId) {

		//TODO implement it!.
		return new Offer();
	}

	@GetMapping(value = "brand/{brandId}/partnumber/{partnumber}/offer", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public List<OfferByPartNumber> getOfferByPartNumber(@PathVariable Integer brandId, @PathVariable String partnumber) {

		//TODO implement it!.
		return new ArrayList<>();
	}
}
