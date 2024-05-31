package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DeleteWarehouseUseCaseImplTest {

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private DeleteWarehouseUseCaseImpl deleteWarehouseUseCase;

  @Test
  void testExecute_validWarehouseId_deleteWarehouse() throws Exception {
    Integer warehouseId = 1;
    Warehouse warehouse = new Warehouse(warehouseId, null, null, null, 10, null);

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));

    deleteWarehouseUseCase.execute(warehouseId);

    verify(mockWarehouseRepository).findById(warehouseId);
    verify(mockWarehouseRepository).delete(warehouse);
  }

  @Test
  void testExecute_invalidWarehouseId_throwsException() throws Exception {
    Integer warehouseId = 1;

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> deleteWarehouseUseCase.execute(warehouseId));

    verify(mockWarehouseRepository).findById(warehouseId);
    verify(mockWarehouseRepository, never()).delete(any());
  }
  
}
