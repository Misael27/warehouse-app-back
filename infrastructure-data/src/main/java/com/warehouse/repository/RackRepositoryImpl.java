package com.warehouse.repository;

import com.warehouse.entity.Rack;
import com.warehouse.mapping.RackTable;
import com.warehouse.repository.springdata.SpringDataRackRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class RackRepositoryImpl implements RackRepository {

  SpringDataRackRepository springDataRackRepository;
  ModelMapper mapper;

  public RackRepositoryImpl(final SpringDataRackRepository springDataRackRepository) {
    this.springDataRackRepository = springDataRackRepository;
    this.mapper = new ModelMapper();
    this.mapper.getConfiguration().setSkipNullEnabled(true);
  }

  @Override
  public void save(Rack rack) {
    final RackTable rackTable = this.mapper.map(rack, RackTable.class);
    this.springDataRackRepository.save(rackTable);
    rack.setId(rackTable.getId());
  }

  @Override
  public Optional<Rack> findById(Integer rackId) {
    return springDataRackRepository.findById(rackId)
        .map(rackTable -> this.mapper.map(rackTable, Rack.class));
  }

  @Override
  public void delete(Rack rack) {
    springDataRackRepository.deleteById(rack.getId());
  }

}
