package com.priceapp.response;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FinalPriceResponse {

	Integer productId;

	Integer brandId;

	Integer priceList;

	Double price;

	LocalDateTime startDate;

	LocalDateTime endDate;

}
