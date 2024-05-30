package com.warehouse.usecase.warehose;

import com.warehouse.entity.Warehouse;

public interface UpdateWarehouseUseCase {

  Warehouse execute(Integer warehouseId, Warehouse warehouse);

}
