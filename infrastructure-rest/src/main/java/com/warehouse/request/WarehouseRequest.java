package com.warehouse.request;

import com.warehouse.entity.EFamily;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class WarehouseRequest {

  private String client;

  private EFamily family;

  private Integer size;
  
}
