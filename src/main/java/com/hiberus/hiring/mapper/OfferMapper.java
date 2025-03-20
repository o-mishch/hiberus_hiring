package com.hiberus.hiring.mapper;

import java.time.Instant;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import com.hiberus.hiring.controller.dto.OfferDto;
import com.hiberus.hiring.entity.Offer;

@Mapper(componentModel = "spring")
public interface OfferMapper {
	OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);

	@Mapping(target = "productPartNumber", source = "product.partNumber")
	@Mapping(target = "currencyIso", source = "currency.isoCode")
	@Mapping(target = "offerId", source = "id")
	@Mapping(target = "brandId", source = "brand.id")
	@Mapping(target = "priceRateId", source = "priceRate.id")
	OfferDto toDto(Offer offer);

	List<OfferDto> toDto(List<Offer> offer);

	@Mapping(target = "startDate", source = "startDate", qualifiedByName = "parseInstant")
	@Mapping(target = "endDate", source = "endDate", qualifiedByName = "parseInstant")
	Offer toEntity(OfferDto offerDto);

	@Named("parseInstant")
	default Instant parseInstant(String date) {
		return Instant.parse(date);
	}
}
