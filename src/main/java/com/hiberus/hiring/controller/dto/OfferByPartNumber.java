package com.hiberus.hiring.controller.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Use this POJO on the reponse for brand & partnumber & offer endPoint.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferByPartNumber implements Serializable {

	private String startDate;

	private String endDate;

	private BigDecimal price;

	private String currencyIso;
}