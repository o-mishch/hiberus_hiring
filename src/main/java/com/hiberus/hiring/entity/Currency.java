package com.hiberus.hiring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CURRENCY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Currency {

	@Id
	@Column(name = "CURR", length = 3, nullable = false, unique = true)
	private String isoCode;

	@Column(name = "NUM", nullable = false)
	private String number;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "LOCATION", nullable = false)
	private String location;
}
