package com.warehouse.repository;

import com.warehouse.entity.Rack;

import java.util.Optional;

public interface RackRepository {
  void save(Rack rack);

  Optional<Rack> findById(Integer rackId);

  void delete(Rack warehouse);

}
