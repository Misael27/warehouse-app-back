package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.CreateWarehouseUseCase;

import java.util.UUID;

public class CreateWarehouseUseCaseImpl implements CreateWarehouseUseCase {

  final WarehouseRepository warehouseRepository;

  public CreateWarehouseUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }

  @Override
  public Warehouse execute(final Warehouse warehouse) {
    warehouse.setUuid(UUID.randomUUID());
    if (!warehouse.isValid()) {
      throw new ResourceBadRequestException("INVALID_REQUEST");
    }
    warehouseRepository.save(warehouse);
    return warehouse;
  }

}
