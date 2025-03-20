package com.hiberus.hiring.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

	@Id
	@Column(name = "PARTNUMBER", nullable = false, unique = true)
	private String partNumber;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany(mappedBy = "product")
	private List<Offer> offers;
}
