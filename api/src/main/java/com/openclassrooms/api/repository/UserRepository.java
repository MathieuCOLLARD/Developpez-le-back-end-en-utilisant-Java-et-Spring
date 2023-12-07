package com.openclassrooms.api.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.api.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}
