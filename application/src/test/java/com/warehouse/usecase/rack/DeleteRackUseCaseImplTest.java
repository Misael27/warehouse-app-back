package com.warehouse.usecase.rack;

import com.warehouse.entity.Rack;
import com.warehouse.exception.ResourceNotFoundException;
import com.warehouse.repository.RackRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeleteRackUseCaseImplTest {

  @Mock
  private RackRepository mockRackRepository;

  @InjectMocks
  private DeleteRackUseCaseImpl deleteRackUseCase;

  @Test
  void testExecute_validRackId_deleteRack() throws Exception {
    Integer rackId = 1;
    Rack rack = new Rack(rackId, null, null, null);

    when(mockRackRepository.findById(rackId)).thenReturn(Optional.of(rack));

    deleteRackUseCase.execute(rackId);

    verify(mockRackRepository).findById(rackId);
    verify(mockRackRepository).delete(rack);
  }

  @Test
  void testExecute_invalidRackId_throwException() throws Exception {
    Integer rackId = 1;

    when(mockRackRepository.findById(rackId)).thenReturn(Optional.empty());

    try {
      deleteRackUseCase.execute(rackId);
      fail("Expected ResourceNotFoundException");
    } catch (ResourceNotFoundException e) {
    }
  }
  
}
