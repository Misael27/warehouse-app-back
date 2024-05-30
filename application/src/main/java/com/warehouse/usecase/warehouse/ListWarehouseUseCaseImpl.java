package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.repository.WarehouseRepository;
import com.warehouse.usecase.warehose.ListWarehouseUseCase;

import java.util.List;

public class ListWarehouseUseCaseImpl implements ListWarehouseUseCase {

  WarehouseRepository warehouseRepository;

  public ListWarehouseUseCaseImpl(WarehouseRepository warehouseRepository) {
    this.warehouseRepository = warehouseRepository;
  }
  
  @Override
  public List<Warehouse> execute() {
    return this.warehouseRepository.findAll();
  }

}
