package com.openclassrooms.api.repository;

import com.openclassrooms.api.entity.RentalEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends CrudRepository<RentalEntity, Long> {

}
