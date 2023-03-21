package com.priceapp.service;

import java.time.LocalDateTime;

import com.priceapp.entity.Price;


public interface IPriceService {

	/**
	 * Find final Price by application date, productId and brandId
	 * @param price
	 */
	Price findFinalPrice(Integer productId, Integer brandId, LocalDateTime applicationDate);

	
}
