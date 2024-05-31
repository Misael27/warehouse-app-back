package com.warehouse.entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class WarehouseTest {

	@Test
	public void testIsValid_allFieldsSet_returnTrue() {
		final Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
		assertTrue(warehouse.isValid());
	}

	@Test
	public void testIsValid_allFieldsSetWithRacks_returnTrue() {
		final Rack rack = new Rack();
		rack.setWarehouseId(1);
		rack.setType(ERackType.A);
		
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 10, List.of(rack));
		assertTrue(warehouse.isValid());
	}

	@Test
	public void testIsValid_nullUuid_returnFalse() {
		Warehouse warehouse = new Warehouse(1, null, "Cliente", EFamily.EST, 10, null);
		assertFalse(warehouse.isValid());
	}

	@Test
	public void testIsValid_nullClient_returnFalse() {
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), null, EFamily.EST, 10, null);
		assertFalse(warehouse.isValid());
	}
	
	@Test
	public void testUpdate_updateAllFields_updateValues() {
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
		Warehouse update = new Warehouse(null, null, "Cliente Nuevo", EFamily.EST, 20, null);
		warehouse.update(update);

		assertEquals("Cliente Nuevo", warehouse.getClient());
		assertEquals(EFamily.EST, warehouse.getFamily());
		assertEquals(20, (int) warehouse.getSize());
	}

	@Test
	public void testUpdate_updatePartialFields_updateSpecificValues() {
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
		Warehouse update = new Warehouse(null, null, null, null, 5, null);
		warehouse.update(update);

		assertEquals("Cliente", warehouse.getClient());
		assertEquals(EFamily.EST, warehouse.getFamily());
		assertEquals(5, (int) warehouse.getSize());
	}

	@Test
	public void testCanAddNewRack_noRacks_returnTrue() {
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 10, null);
		assertTrue(warehouse.canAddNewRack());
	}

	@Test
	public void testCanAddNewRack_fullCapacity_returnFalse() {
		Warehouse warehouse = new Warehouse(1, UUID.randomUUID(), "Cliente", EFamily.EST, 2, List.of(new Rack(), new Rack()));
		assertFalse(warehouse.canAddNewRack());
	}
	
}
