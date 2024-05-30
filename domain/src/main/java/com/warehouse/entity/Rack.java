package com.warehouse.entity;

import lombok.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode()
@ToString(of = "id")
@Getter
@Setter
public class Rack implements ICommonDomain {

  private Integer id;

  private Integer warehouseId;

  private UUID uuid;

  private ERackType type;

  @Override
  public boolean isValid() {
    return warehouseId != null && uuid != null && type != null;
  }

  public boolean isValidWarehouse(final Warehouse warehouse) {
    return warehouse.getId().equals(this.warehouseId) 
        && Rack.getRackTypesByWarehouseFamily(warehouse.getFamily()).contains(this.type);
  }
  
  public static List<ERackType> getRackTypesByWarehouseFamily(final EFamily family) {
    return switch (family) {
      case EST -> List.of(ERackType.A, ERackType.B, ERackType.C);
      case ROB -> List.of(ERackType.A, ERackType.C, ERackType.D);
      default -> List.of();
    };
  }

}
