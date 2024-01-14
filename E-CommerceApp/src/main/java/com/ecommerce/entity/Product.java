package com.ecommerce.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
	@Id
	private Long pid;
	private String title;
	private String description;
	private Long price;
	private Double discountPercentage;
	private Double rating;
	private String brand;
	private Long stock;
	private String category;
	private String thumbnail;
}
