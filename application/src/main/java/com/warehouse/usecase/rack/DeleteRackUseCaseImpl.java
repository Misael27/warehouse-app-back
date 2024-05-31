package com.warehouse.usecase.rack;

import com.warehouse.entity.Rack;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.RackRepository;

public class DeleteRackUseCaseImpl implements DeleteRackUseCase{
  
  final RackRepository rackRepository;

  public DeleteRackUseCaseImpl(RackRepository rackRepository) {
    this.rackRepository = rackRepository;
  }

  @Override
  public void execute(Integer rackId) {
    final Rack warehouse = this.rackRepository.findById(rackId)
        .orElseThrow(() -> new ResourceNotFoundException("rackId "+rackId+" not found", "RACK_NOT_FOUND"));
    this.rackRepository.delete(warehouse);
  }

}
