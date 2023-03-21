package com.priceapp.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.priceapp.entity.Price;

/**
 * 
 * @author mjpol
 *
 */
public interface IPriceRepository {

	Optional<Price> findFinalPriceByDateAndProductIdAndBrandId(Integer productId, Integer brandId, LocalDateTime applicationDate);
	
}
