package com.priceapp.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = { com.priceapp.configuration.Application.class })
@AutoConfigureMockMvc
@SqlGroup({ @Sql(value = "classpath:empty/reset.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD),
		@Sql(value = "classpath:init/price-data.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD) })
public class PriceControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@ParameterizedTest
	@CsvFileSource(resources = "/data-test.csv", numLinesToSkip = 1)
	void should_be_able_to_get_price_for_produc(String productId, String brandId, String applicationDate,
			String priceListExpected, String priceExpected, String startDateExpected, String endDateExpected)
			throws Exception {
		// Arrange
		// Act
		var result = this.mockMvc.perform(get("/price/product/{productId}", productId).param("brandId", brandId)
				.param("applicationDate", applicationDate)).andDo(print());

		// Asserts
		result.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.productId").value(productId)).andExpect(jsonPath("$.brandId").value(brandId))
				.andExpect(jsonPath("$.priceList").value(priceListExpected))
				.andExpect(jsonPath("$.price").value(priceExpected))
				.andExpect(jsonPath("$.startDate").value(startDateExpected))
				.andExpect(jsonPath("$.endDate").value(endDateExpected));
	}

}
