package com.kobiguard.app.repository;

import com.kobiguard.app.entity.Address;
import com.kobiguard.app.entity.Attribute;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AttributeRepository extends CrudRepository<Attribute, String>, PagingAndSortingRepository<Attribute, String> {
}
