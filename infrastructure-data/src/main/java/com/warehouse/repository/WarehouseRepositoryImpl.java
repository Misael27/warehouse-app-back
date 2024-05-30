package com.warehouse.repository;

import com.warehouse.entity.Rack;
import com.warehouse.entity.Warehouse;
import com.warehouse.mapping.WarehouseTable;
import com.warehouse.repository.springdata.SpringDataWarehouseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class WarehouseRepositoryImpl implements WarehouseRepository {

  final SpringDataWarehouseRepository springDataWarehouseRepository;
  final ModelMapper mapper;

  public WarehouseRepositoryImpl(final SpringDataWarehouseRepository springDataWarehouseRepository) {
    this.springDataWarehouseRepository = springDataWarehouseRepository;
    this.mapper = new ModelMapper();
    this.mapper.getConfiguration().setSkipNullEnabled(true);
  }

  @Override
  public void save(final Warehouse warehouse) {
    final WarehouseTable warehouseTable = this.mapper.map(warehouse, WarehouseTable.class);
    this.springDataWarehouseRepository.save(warehouseTable);
    warehouse.setId(warehouseTable.getId());
  }

  @Override
  public List<Warehouse> findAll() {
    var result = new ArrayList<Warehouse>();
    springDataWarehouseRepository.findAll().forEach(warehouseTable -> result.add(mapper.map(warehouseTable, Warehouse.class)));
    return result;
  }

  @Override
  public Optional<Warehouse> findById(Integer warehouseId) {
    return springDataWarehouseRepository.findById(warehouseId)
        .map(warehouseTable -> {
          var warehouse = this.mapper.map(warehouseTable, Warehouse.class);
          warehouse.setRackList(warehouseTable.getRackList().stream().map(rackTable -> this.mapper.map(rackTable, Rack.class)).toList());
          return warehouse;
        });
  }

  @Override
  public void delete(Warehouse warehouse) {
    springDataWarehouseRepository.deleteById(warehouse.getId());
  }

}
