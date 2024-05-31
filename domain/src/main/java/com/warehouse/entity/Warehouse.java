package com.warehouse.entity;

import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@ToString(of = "id")
@Getter
@Setter
public class Warehouse implements ICommonDomain {

  private Integer id;
  
  private UUID uuid;
  
  private String client;
  
  private EFamily family;
  
  private Integer size;
  
  private List<Rack> rackList;

  @Override
  public boolean isValid() {
    return uuid != null && client != null && family != null && size != null 
        && (rackList == null || (rackList.size() <= size && rackList.stream().allMatch(r -> r.isValidWarehouse(this))));
  }

  public void update(Warehouse warehouseUpdate) {
    this.client = !Objects.isNull(warehouseUpdate.getClient()) ? warehouseUpdate.getClient() : this.client;
    this.family = !Objects.isNull(warehouseUpdate.getFamily()) ? warehouseUpdate.getFamily() : this.family;
    this.size = !Objects.isNull(warehouseUpdate.getSize()) ? warehouseUpdate.getSize() : this.size;
  }

  public boolean canAddNewRack() {
    return rackList == null || rackList.size() < size;
  }

}
