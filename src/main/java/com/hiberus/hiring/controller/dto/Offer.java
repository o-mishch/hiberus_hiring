package com.hiberus.hiring.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

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
public class Offer implements Serializable {

	private Long offerId;

	private Integer brandId;

	private String startDate;

	private String endDate;

	private Long priceListId;

	private String productPartnumber;

	private Integer priority;

	private BigDecimal price;

	private String currencyIso;
}