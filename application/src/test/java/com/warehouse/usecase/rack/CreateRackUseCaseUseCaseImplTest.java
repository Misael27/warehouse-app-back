package com.warehouse.usecase.rack;

import com.warehouse.entity.EFamily;
import com.warehouse.entity.ERackType;
import com.warehouse.entity.Rack;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.RackRepository;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateRackUseCaseUseCaseImplTest {

  @Mock
  private RackRepository mockRackRepository;

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private CreateRackUseCaseUseCaseImpl createRackUseCase;

  @Test
  void testExecute_validRack_returnSavedRack() {
    Warehouse warehouse = new Warehouse(10, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
    Rack rack = new Rack(null, 10, null, ERackType.A);
    rack.setWarehouseId(warehouse.getId());

    when(mockWarehouseRepository.findById(rack.getWarehouseId())).thenReturn(Optional.of(warehouse));

    Rack savedRack = createRackUseCase.execute(rack);

    assertNotNull(savedRack);
    assertEquals(rack, savedRack);
    verify(mockWarehouseRepository).findById(warehouse.getId());
    verify(mockRackRepository).save(rack);
  }

  @Test
  void testExecute_warehouseNotFound_throwException() {
    Rack rack = new Rack(null, 10, null, ERackType.A);
    rack.setWarehouseId(1);

    when(mockWarehouseRepository.findById(rack.getWarehouseId())).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> createRackUseCase.execute(rack));
  }
  
  @Test
  void testExecute_warehouseFull_throwException() {
    Warehouse warehouse = new Warehouse(10, UUID.randomUUID(), "Cliente", EFamily.EST, 2, List.of(new Rack(), new Rack()));
    Rack rack = new Rack(null, 10, null, ERackType.A);
    rack.setWarehouseId(warehouse.getId());

    when(mockWarehouseRepository.findById(rack.getWarehouseId())).thenReturn(Optional.of(warehouse));
    
    assertThrows(ResourceBadRequestException.class, () -> createRackUseCase.execute(rack));
  }
  
}
