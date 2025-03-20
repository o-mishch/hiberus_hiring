package com.hiberus.hiring.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Use this POJO for offer service end point responses.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto implements Serializable {

	@NotNull
	private Long offerId;

	@NotNull
	private Integer brandId;

	@NotNull
	private String startDate;

	@NotNull
	private String endDate;

	@NotNull
	@JsonProperty("priceListId")
	private Long priceRateId;

	@NotNull
	@JsonProperty("productPartnumber")
	private String productPartNumber;

	@NotNull
	private Integer priority;

	@NotNull
	private BigDecimal price;

	@NotNull
	private String currencyIso;

	@AssertTrue
	public boolean isValid() {
		try {
			Instant start = Instant.parse(startDate);
			Instant end = Instant.parse(endDate);
			return start.isBefore(end);
		}
		catch (Exception ignore) {
			return false;
		}
	}
}