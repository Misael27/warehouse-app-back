package com.priceapp.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import com.priceapp.entity.Price;
import com.priceapp.mapping.PriceTable;
import com.priceapp.repository.springdata.SpringDataPriceRepository;

@Repository
public class PriceRepository implements IPriceRepository {

	SpringDataPriceRepository springDataPriceRepository;
	ModelMapper mapper;

	public PriceRepository(SpringDataPriceRepository springDataPriceRepository) {
		this.springDataPriceRepository = springDataPriceRepository;
		this.mapper = new ModelMapper();
		this.mapper.getConfiguration().setSkipNullEnabled(true);
	}

	@Override
	public Optional<Price> findFinalPriceByDateAndProductIdAndBrandId(Integer productId, Integer brandId,
			LocalDateTime applicationDate) {
		PriceTable result = springDataPriceRepository
				.findFinalPriceByBrandAndProductAndDate(productId, brandId, applicationDate, PageRequest.of(0, 1))
				.stream().findFirst().orElse(null);
		Price price = result != null ? mapper.map(result, Price.class) : null;
		return Optional.ofNullable(price);
	}

}
