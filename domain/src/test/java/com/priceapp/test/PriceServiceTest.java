package com.priceapp.test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.priceapp.entity.ECurrency;
import com.priceapp.entity.Price;
import com.priceapp.exception.ResourceNotFoundException;
import com.priceapp.repository.IPriceRepository;
import com.priceapp.service.impl.PriceService;
import org.junit.jupiter.api.Assertions;


@ExtendWith(MockitoExtension.class)
public class PriceServiceTest {

	@InjectMocks
	private PriceService sut;

	@Mock
	private IPriceRepository priceRepository;


	@Test
	void when_find_by_productId_brandId_and_date_should_return_price_success() {
		
		// Arrange
		Integer productId = 35455; 
		Integer brandId = 1; 
		LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
		Optional<Price> priceReturn = Optional.of(new Price(1, 1, LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0),
				LocalDateTime.of(2020, Month.DECEMBER, 31, 23, 59, 59), 1, 35455, 0, 35455.00, ECurrency.EUR));
		when(priceRepository.findFinalPriceByDateAndProductIdAndBrandId(any(Integer.class), any(Integer.class), any(LocalDateTime.class))).thenReturn(priceReturn);

		// Act
		var result = sut.findFinalPrice(productId, brandId, applicationDate);

		// Asserts
		Assertions.assertNotNull(result);
		Assertions.assertEquals(priceReturn.get(), result);
	}
	
	@Test
	void when_find_by_productId_brandId_and_date_should_return_exception_not_found() {
		
		// Arrange
		Integer productId = 35455; 
		Integer brandId = 1; 
		LocalDateTime applicationDate = LocalDateTime.of(2020, Month.JUNE, 14, 0, 0, 0);
		Price price = null;
		Optional<Price> priceReturn = Optional.ofNullable(price);
		when(priceRepository.findFinalPriceByDateAndProductIdAndBrandId(any(Integer.class), any(Integer.class), any(LocalDateTime.class))).thenReturn(priceReturn);

		// Act
	    Throwable exception = Assertions.assertThrows(ResourceNotFoundException.class, () -> sut.findFinalPrice(productId, brandId, applicationDate));

		// Asserts
	    Assertions.assertEquals("No price for Product "+productId, exception.getMessage());

	}


}
