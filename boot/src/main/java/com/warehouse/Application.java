package com.warehouse;

import com.warehouse.repository.RackRepository;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.rack.CreateRackUseCase;
import com.warehouse.usecase.rack.CreateRackUseCaseUseCaseImpl;
import com.warehouse.usecase.warehose.*;
import com.warehouse.usecase.warehouse.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * Application
 * 
 * @author mjpol
 *
 */
@SpringBootApplication(scanBasePackages = {"com.warehouse.*"})
@PropertySources({
    @PropertySource("classpath:application.properties")
})
@OpenAPIDefinition(info = @Info(title = "Warehouse App Back", version = "1.0", description = ""))
public class Application {	

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CreateWarehouseUseCase createWarehouseUseCase(final WarehouseRepository warehouseRepository) {
		return new CreateWarehouseUseCaseImpl(warehouseRepository);
	}

	@Bean
	DeleteWarehouseUseCase deleteWarehouseUseCase(final WarehouseRepository warehouseRepository) {
		return new DeleteWarehouseUseCaseImpl(warehouseRepository);
	}

	@Bean
	GetWarehouseByIdUseCase getWarehouseByIdUseCase(final WarehouseRepository warehouseRepository) {
		return new GetWarehouseByIdUseCaseImpl(warehouseRepository);
	}

	@Bean
	ListWarehouseUseCase listWarehouseUseCase(final WarehouseRepository warehouseRepository) {
		return new ListWarehouseUseCaseImpl(warehouseRepository);
	}

	@Bean
	UpdateWarehouseUseCase updateWarehouseUseCase(final WarehouseRepository warehouseRepository) {
		return new UpdateWarehouseUseCaseImpl(warehouseRepository);
	}

	@Bean
	CreateRackUseCase createRackUseCase(final RackRepository rackRepository, final WarehouseRepository warehouseRepository) {
		return new CreateRackUseCaseUseCaseImpl(rackRepository, warehouseRepository);
	}

	@Bean
	GetRackPermutationsUseCase getRackPermutationsUseCase(final WarehouseRepository warehouseRepository) {
		return new GetRackPermutationsUseCaseImpl(warehouseRepository);
	}
	
}
