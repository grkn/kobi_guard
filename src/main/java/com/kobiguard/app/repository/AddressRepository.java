package com.kobiguard.app.repository;

import com.kobiguard.app.entity.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, String> {
}
