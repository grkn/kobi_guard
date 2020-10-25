package com.kobiguard.app.repository;

import com.kobiguard.app.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductRepository extends CrudRepository<Product, String>, PagingAndSortingRepository<Product, String> {

}
