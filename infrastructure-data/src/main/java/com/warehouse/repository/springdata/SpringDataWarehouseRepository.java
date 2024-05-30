package com.warehouse.repository.springdata;

import com.warehouse.mapping.WarehouseTable;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataWarehouseRepository extends CrudRepository<WarehouseTable, Integer> {

  
  
}
