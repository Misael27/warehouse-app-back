package com.priceapp.repository.springdata;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.priceapp.mapping.PriceTable;

public interface SpringDataPriceRepository extends PagingAndSortingRepository<PriceTable, Long> {

	@Query("""
			SELECT new com.priceapp.mapping.PriceTable(p.productId, p.brandId, p.priceList, p.price, p.startDate, p.endDate)
			     FROM PriceTable p
			     WHERE p.productId = ?1 AND p.brandId = ?2 AND ?3 BETWEEN p.startDate AND p.endDate
			     ORDER BY p.priority DESC
			     """)
	List<PriceTable> findFinalPriceByBrandAndProductAndDate(Integer productId, Integer brandId,
			LocalDateTime applicationDate, Pageable pageable);

}
