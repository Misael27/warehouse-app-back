package com.warehouse.mapping;

import com.warehouse.entity.ERackType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "RACK")
public class RackTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "type")
  private ERackType type;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "warehouseId", referencedColumnName = "id") // Update the column name if different
  private WarehouseTable warehouse;
  
}
