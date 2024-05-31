package com.warehouse.usecase.warehouse;

import com.warehouse.entity.Warehouse;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListWarehouseUseCaseImplTest {

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private ListWarehouseUseCaseImpl listWarehouseUseCase;

  @Test
  void testExecute_shouldReturnEmptyListIfNoWarehouses() throws Exception {
    when(mockWarehouseRepository.findAll()).thenReturn(Collections.emptyList());

    List<Warehouse> warehouses = listWarehouseUseCase.execute();

    assertNotNull(warehouses);
    assertTrue(warehouses.isEmpty());
    verify(mockWarehouseRepository).findAll();
  }

  @Test
  void testExecute_shouldReturnListOfWarehouses() throws Exception {
    List<Warehouse> warehouses = Arrays.asList(new Warehouse(), new Warehouse());
    when(mockWarehouseRepository.findAll()).thenReturn(warehouses);

    List<Warehouse> returnedWarehouses = listWarehouseUseCase.execute();

    assertNotNull(returnedWarehouses);
    assertEquals(warehouses.size(), returnedWarehouses.size()); // Verify same size
    verify(mockWarehouseRepository).findAll();
  }
  
}
