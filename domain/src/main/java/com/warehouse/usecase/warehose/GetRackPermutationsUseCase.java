package com.warehouse.usecase.warehose;

import java.util.List;

public interface GetRackPermutationsUseCase {

  List<String> execute(Integer warehouseId);
  
}
