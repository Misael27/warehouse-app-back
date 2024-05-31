package com.warehouse.usecase.warehouse;

import com.warehouse.entity.EFamily;
import com.warehouse.entity.Warehouse;
import com.warehouse.exception.ResourceBadRequestException;
import com.warehouse.repository.WarehouseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CreateWarehouseUseCaseImplTest {

  @Mock
  private WarehouseRepository mockWarehouseRepository;

  @InjectMocks
  private CreateWarehouseUseCaseImpl createWarehouseUseCase;

  @Test
  void testExecute_validWarehouse_returnSavedWarehouse() {
    Warehouse warehouse = new Warehouse(10, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);

    createWarehouseUseCase.execute(warehouse);

    verify(mockWarehouseRepository).save(warehouse);
  }

  @Test
  void testExecute_invalidWarehouse_throwsException() {
    Warehouse warehouse = new Warehouse(null, null, null, null, 10, null);

    assertThrows(ResourceBadRequestException.class, () -> createWarehouseUseCase.execute(warehouse));

    verify(mockWarehouseRepository, never()).save(warehouse);
  }
  
}
