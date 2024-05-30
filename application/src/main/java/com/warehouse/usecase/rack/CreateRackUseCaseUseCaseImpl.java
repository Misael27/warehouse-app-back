package com.warehouse.usecase.rack;

import com.warehouse.entity.Rack;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.RackRepository;
import com.warehouse.repository.WarehouseRepository;

import java.util.UUID;

public class CreateRackUseCaseUseCaseImpl implements CreateRackUseCase {

  final RackRepository rackRepository;
  final WarehouseRepository warehouseRepository;


  public CreateRackUseCaseUseCaseImpl(RackRepository rackRepository, WarehouseRepository warehouseRepository) {
    this.rackRepository = rackRepository;
    this.warehouseRepository = warehouseRepository;
  }
  
  @Override
  public Rack execute(final Rack rack) {
    final Warehouse warehouse = this.warehouseRepository.findById(rack.getWarehouseId())
        .orElseThrow(() -> new ResourceNotFoundException("warehouseId "+rack.getWarehouseId()+" not found", "WAREHOUSE_NOT_FOUND"));
    rack.setUuid(UUID.randomUUID());
    if (!rack.isValid()) {
      throw new ResourceBadRequestException("INVALID_REQUEST");
    }
    if (!rack.isValidWarehouse(warehouse)) {
      throw new ResourceBadRequestException("INVALID_WAREHOUSE_TYPE");
    }
    if (!warehouse.canAddNewRack()) {
      throw new ResourceBadRequestException("INVALID_WAREHOUSE_SIZE");
    }
    rack.setId(null);
    rackRepository.save(rack);
    return rack;
  }

}
