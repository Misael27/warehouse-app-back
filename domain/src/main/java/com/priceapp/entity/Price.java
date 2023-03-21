package com.priceapp.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 
 * @author mjpol
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString(of = "id")
@Getter
@Setter
public class Price implements ICommonDomain {

	private Integer id;
	private Integer brandId;
    private LocalDateTime startDate;
	private LocalDateTime endDate;
	private Integer priceList;
	private Integer productId;
	private Integer priority;
	private Double price;
	private ECurrency currency;

	@Override
	public boolean isValid() {
		if (Objects.isNull(brandId) || Objects.isNull(startDate) || Objects.isNull(endDate) || startDate.isAfter(endDate)
				|| Objects.isNull(priceList) || Objects.isNull(productId) || Objects.isNull(priority)
				|| Objects.isNull(price) || price < 0 || Objects.isNull(currency)) {
			return false;
		}
		return true;
	}

}
