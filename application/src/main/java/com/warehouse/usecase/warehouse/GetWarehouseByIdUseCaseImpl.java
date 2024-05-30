package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.GetWarehouseByIdUseCase;

public class GetWarehouseByIdUseCaseImpl implements GetWarehouseByIdUseCase {

  WarehouseRepository warehouseRepository;

  public GetWarehouseByIdUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }
  
  @Override
  public Warehouse execute(final Integer warehouseId) {
    return this.warehouseRepository.findById(warehouseId)
        .orElseThrow(() -> new ResourceNotFoundException("warehouseId "+warehouseId+" not found", "WAREHOUSE_NOT_FOUND"));
  }
  
}
