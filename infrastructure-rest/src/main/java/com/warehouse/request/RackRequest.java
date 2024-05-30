package com.warehouse.request;

import com.warehouse.entity.ERackType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class RackRequest {

  private Integer warehouseId;

  private ERackType type;

}
