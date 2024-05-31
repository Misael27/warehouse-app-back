package com.warehouse.entity;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RackTest {

  @Test
  void testIsValid_allFieldsSet_returnTrue() {
    Rack rack = new Rack(1, 10, UUID.randomUUID(), ERackType.A);
    assertTrue(rack.isValid());
  }

  @Test
  void testIsValid_nullUuid_returnFalse() {
    Rack rack = new Rack(1, 10, null, ERackType.A);
    assertFalse(rack.isValid());
  }

  @Test
  void testIsValid_nullType_returnFalse() {
    Rack rack = new Rack(1, 10, UUID.randomUUID(), null);
    assertFalse(rack.isValid());
  }

  @Test
  void testIsValidWarehouse_correctWarehouse_returnTrue() {
    Warehouse warehouse = new Warehouse(10, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
    Rack rack = new Rack(1, 10, UUID.randomUUID(), ERackType.A);
    assertTrue(rack.isValidWarehouse(warehouse));
  }

  @Test
  void testIsValidWarehouse_incorrectWarehouseId_returnFalse() {
    Warehouse warehouse = new Warehouse(20, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
    Rack rack = new Rack(1, 10, UUID.randomUUID(), ERackType.A);
    assertFalse(rack.isValidWarehouse(warehouse));
  }

  @Test
  void testGetRackTypesByWarehouseFamily_EST_returnCorrectTypes() {
    List<ERackType> expectedTypes = List.of(ERackType.A, ERackType.B, ERackType.C);
    List<ERackType> actualTypes = Rack.getRackTypesByWarehouseFamily(EFamily.EST);
    assertEquals(expectedTypes, actualTypes);
  }

  @Test
  void testGetRackTypesByWarehouseFamily_ROB_returnCorrectTypes() {
    List<ERackType> expectedTypes = List.of(ERackType.A, ERackType.C, ERackType.D);
    List<ERackType> actualTypes = Rack.getRackTypesByWarehouseFamily(EFamily.ROB);
    assertEquals(expectedTypes, actualTypes);
  }
  
}
