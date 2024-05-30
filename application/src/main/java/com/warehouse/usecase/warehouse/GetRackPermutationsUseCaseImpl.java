package com.warehouse.usecase.warehouse;

import com.warehouse.entity.ERackType;
import com.warehouse.entity.Rack;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.GetRackPermutationsUseCase;

import java.util.ArrayList;
import java.util.List;

public class GetRackPermutationsUseCaseImpl implements GetRackPermutationsUseCase {

  final WarehouseRepository warehouseRepository;

  public GetRackPermutationsUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }
  
  @Override
  public List<String> execute(Integer warehouseId) {
    final Warehouse warehouse = this.warehouseRepository.findById(warehouseId)
        .orElseThrow(() -> new ResourceNotFoundException("warehouseId "+warehouseId+" not found", "WAREHOUSE_NOT_FOUND"));
    
    List<String> result = new ArrayList<>();
    this.calculateRackPermutations("", result,warehouse.getSize(), Rack.getRackTypesByWarehouseFamily(warehouse.getFamily()));
    return result;
  }

  private void calculateRackPermutations(final String currentPerm, final List<String> result, final Integer size, final List<ERackType> rackTypes) {
    if (currentPerm.length() == size) {
      result.add(currentPerm);
    }
    else {
      for (var rackType : rackTypes) {
        this.calculateRackPermutations(currentPerm + rackType.toString(), result, size, rackTypes);
      }
    }
  }

}
