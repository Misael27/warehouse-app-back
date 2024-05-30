package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.UpdateWarehouseUseCase;

public class UpdateWarehouseUseCaseImpl implements UpdateWarehouseUseCase {

  WarehouseRepository warehouseRepository;

  public UpdateWarehouseUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }

  @Override
  public Warehouse execute(final Integer warehouseId, final Warehouse warehouseUpdate) {
    final Warehouse warehouse = this.warehouseRepository.findById(warehouseId)
        .orElseThrow(() -> new ResourceNotFoundException("warehouseId "+warehouseId+" not found", "WAREHOUSE_NOT_FOUND"));
    warehouse.update(warehouseUpdate);
    if(!warehouse.isValid()) {
      throw new ResourceBadRequestException("INVALID_REQUEST");
    }
    this.warehouseRepository.save(warehouse);
    return warehouse;
  }

}
