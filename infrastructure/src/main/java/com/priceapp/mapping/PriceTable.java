package com.priceapp.mapping;

import lombok.*;

import java.time.LocalDateTime;

import com.priceapp.entity.ECurrency;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = { "startDate", "endDate" })
@ToString(of = "id")
@Entity
@Table(name = "PRICES")
public class PriceTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "BRAND_ID", nullable = false)
	private Integer brandId;

	@Column(name = "START_DATE", nullable = false)
	private LocalDateTime startDate;

	@Column(name = "END_DATE", nullable = false)
	private LocalDateTime endDate;

	@Column(name = "PRICE_LIST", nullable = false)
	private Integer priceList;

	@Column(name = "PRODUCT_ID", nullable = false)
	private Integer productId;

	@Column(name = "PRIORITY", nullable = false)
	private Integer priority;

	@Column(name = "PRICE", nullable = false)
	private Double price;

	@Column(name = "CURR", nullable = false)
	@Enumerated(EnumType.STRING)
	private ECurrency currency;

	public PriceTable(Integer productId, Integer brandId, Integer priceList, Double price, LocalDateTime startDate,
			LocalDateTime endDate) {
		this.productId = productId;
		this.brandId = brandId;
		this.priceList = priceList;
		this.price = price;
		this.startDate = startDate;
		this.endDate = endDate;
	}

}
