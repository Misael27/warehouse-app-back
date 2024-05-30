package com.warehouse.usecase.warehose;

import com.warehouse.entity.Warehouse;

public interface GetWarehouseByIdUseCase {

  Warehouse execute(Integer id);

}
