package com.warehouse.usecase.warehouse;

import com.warehouse.entity.EFamily;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class GetRackPermutationsUseCaseImplTest {

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private GetRackPermutationsUseCaseImpl getRackPermutationsUseCase;

  @Test
  void testExecute_validWarehouseId_returnPermutations() throws Exception {
    Integer warehouseId = 1;
    Warehouse warehouse = new Warehouse(warehouseId, null, null, EFamily.EST, 3, null); // Assuming EFamily exists

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.of(warehouse));

    List<String> result = getRackPermutationsUseCase.execute(warehouseId);
    
    assertEquals(27, result.size());
    assertTrue(result.contains("AAA"));
    assertTrue(result.contains("AAB"));
    assertTrue(result.contains("ABA"));

    verify(mockWarehouseRepository).findById(warehouseId);
  }

  @Test
  void testExecute_invalidWarehouseId_throwsException() throws Exception {
    Integer warehouseId = 1;

    when(mockWarehouseRepository.findById(warehouseId)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> getRackPermutationsUseCase.execute(warehouseId));

    verify(mockWarehouseRepository).findById(warehouseId);
  }
  
}
