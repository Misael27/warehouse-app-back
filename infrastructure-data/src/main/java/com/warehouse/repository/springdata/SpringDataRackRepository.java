package com.warehouse.repository.springdata;

import com.warehouse.mapping.RackTable;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataRackRepository extends CrudRepository<RackTable, Integer> {
}
