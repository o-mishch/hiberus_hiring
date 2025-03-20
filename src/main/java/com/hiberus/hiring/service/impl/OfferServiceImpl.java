package com.hiberus.hiring.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hiberus.hiring.controller.dto.OfferByPartNumber;
import com.hiberus.hiring.controller.dto.OfferDto;
import com.hiberus.hiring.entity.Offer;
import com.hiberus.hiring.mapper.OfferMapper;
import com.hiberus.hiring.repository.BrandRepository;
import com.hiberus.hiring.repository.CurrencyRepository;
import com.hiberus.hiring.repository.OfferRepository;
import com.hiberus.hiring.repository.PriceRateRepository;
import com.hiberus.hiring.repository.ProductRepository;
import com.hiberus.hiring.service.OfferService;
import com.hiberus.hiring.utils.TimetableFlattenUtils;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

	private final PriceRateRepository priceRateRepository;
	private final CurrencyRepository currencyRepository;
	private final ProductRepository productRepository;
	private final BrandRepository brandRepository;
	private final OfferRepository offerRepository;
	private final OfferMapper offerMapper;

	@Override
	@Transactional
	public void createNewOffer(OfferDto offerDto) {
		Offer offer = offerMapper.toEntity(offerDto);

		enricher(offer, offerDto);

		offerRepository.save(offer);
	}

	@Override
	public void deleteAllOffers() {
		offerRepository.deleteAll();
	}

	@Override
	@Transactional
	public void deleteOfferById(Long id) {
		//		offerRepository.deleteById(id);
		Offer offer = offerRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Offer with ID " + id + " not found."));
		offerRepository.delete(offer);
	}

	@Override
	public List<OfferDto> getAllOffers() {
		return offerMapper.toDto(offerRepository.findAll());
	}

	@Override
	public OfferDto getOfferById(Long offerId) {
		return offerRepository.findById(offerId).map(offerMapper::toDto)
				.orElseThrow(() -> new EntityNotFoundException("Offer with id " + offerId + " not found"));
	}

	@Transactional(readOnly = true)
	@Override
	public List<OfferByPartNumber> getOfferByPartNumber(Integer brandId, String partNumber) {
		List<Offer> offers = offerRepository.findByBrandIdAndProductPartNumber(brandId, partNumber);
		return TimetableFlattenUtils.getOfferByPartNumber(offers);
	}

	private void enricher(Offer target, OfferDto source) {
		brandRepository.findById(source.getBrandId().longValue())
				.ifPresentOrElse(target::setBrand, () -> {
					throw new EntityNotFoundException("not found Brand " + source.getBrandId());
				});
		productRepository.findByPartNumber(source.getProductPartNumber())
				.ifPresentOrElse(target::setProduct, () -> {
					throw new EntityNotFoundException("not found Product " + source.getProductPartNumber());
				});
		currencyRepository.findByIsoCode(source.getCurrencyIso())
				.ifPresentOrElse(target::setCurrency, () -> {
					throw new EntityNotFoundException("not found Currency " + source.getCurrencyIso());
				});
		priceRateRepository.findById(source.getPriceRateId())
				.ifPresentOrElse(target::setPriceRate, () -> {
					throw new EntityNotFoundException("not found PriceList " + source.getPriceRateId());
				});
	}
}
