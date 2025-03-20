package com.hiberus.hiring.entity;

import java.math.BigDecimal;
import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "OFFER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "BRAND_ID", nullable = false)
	private Brand brand;

	@Column(name = "START_DATE", nullable = false)
	private Instant startDate;

	@Column(name = "END_DATE", nullable = false)
	private Instant endDate;

	@ManyToOne
	@JoinColumn(name = "PRICE_LIST", nullable = false)
	private PriceRate priceRate;

	@ManyToOne
	@JoinColumn(name = "PARTNUMBER", nullable = false)
	private Product product;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "PRICE", nullable = false, precision = 10, scale = 2)
	private BigDecimal price;

	@ManyToOne
	@JoinColumn(name = "CURR", nullable = false)
	private Currency currency;
}
