package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.DeleteWarehouseUseCase;

public class DeleteWarehouseUseCaseImpl implements DeleteWarehouseUseCase {

  final WarehouseRepository warehouseRepository;

  public DeleteWarehouseUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }
  
  @Override
  public void execute(final Integer warehouseId) {
    final Warehouse warehouse = this.warehouseRepository.findById(warehouseId)
        .orElseThrow(() -> new ResourceNotFoundException("warehouseId "+warehouseId+" not found", "WAREHOUSE_NOT_FOUND"));
    this.warehouseRepository.delete(warehouse);
  }
  
}
