package com.warehouse.repository;

import com.warehouse.entity.Warehouse;

import java.util.List;
import java.util.Optional;

public interface WarehouseRepository {
  void save(Warehouse warehouse);

  List<Warehouse> findAll();

  Optional<Warehouse> findById(Integer warehouseId);

  void delete(Warehouse warehouse);
}
