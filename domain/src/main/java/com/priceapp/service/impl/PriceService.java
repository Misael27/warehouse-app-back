package com.priceapp.service.impl;

import java.time.LocalDateTime;

import com.priceapp.entity.Price;
import com.priceapp.exception.ResourceNotFoundException;
import com.priceapp.repository.IPriceRepository;
import com.priceapp.service.IPriceService;

public class PriceService implements IPriceService {

	IPriceRepository priceRepository;

	public PriceService(IPriceRepository priceRepository) {
		this.priceRepository = priceRepository;
	}

	@Override
	public Price findFinalPrice(Integer productId, Integer brandId, LocalDateTime applicationDate) {
		final Price price = priceRepository
				.findFinalPriceByDateAndProductIdAndBrandId(productId, brandId, applicationDate)
				.orElseThrow(() -> 
					new ResourceNotFoundException("No price for Product " + productId, "PRICE_NOT_FOUND"));
		return price;
	}

}
