package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdateWarehouseUseCaseImplTest {

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private UpdateWarehouseUseCaseImpl updateWarehouseUseCase;

  @Test
  void testExecute_invalidWarehouseId_throwsException() throws Exception {
    Integer warehouseId = 1;
    Warehouse updateData = new Warehouse(null, UUID.randomUUID(), null, null, null, null);

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> updateWarehouseUseCase.execute(warehouseId, updateData));

    verify(mockWarehouseRepository).findById(warehouseId);
    verify(mockWarehouseRepository, never()).save(any());
  }

  @Test
  void testExecute_invalidUpdateData_throwsException() throws Exception {
    Integer warehouseId = 1;
    Warehouse existingWarehouse = new Warehouse(warehouseId, null, null, null, 10, null);
    Warehouse updateData = new Warehouse(null, null, null, null, null, null); // All null values

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.of(existingWarehouse));

    assertThrows(ResourceBadRequestException.class, () -> updateWarehouseUseCase.execute(warehouseId, updateData));

    verify(mockWarehouseRepository).findById(warehouseId);
    verify(mockWarehouseRepository, never()).save(any());
  }
  
}
