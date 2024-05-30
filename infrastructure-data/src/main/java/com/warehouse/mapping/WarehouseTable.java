package com.warehouse.mapping;

import com.warehouse.entity.EFamily;
import com.warehouse.entity.Rack;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@ToString(of = "id")
@Entity
@Table(name = "WAREHOUSE")
public class WarehouseTable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "uuid")
  private UUID uuid;

  @Column(name = "client")
  private String client;

  @Column(name = "family")
  private EFamily family;

  @Column(name = "size")
  private Integer size;

  @OneToMany(mappedBy = "warehouse", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
  private List<RackTable> rackList;
  
}
