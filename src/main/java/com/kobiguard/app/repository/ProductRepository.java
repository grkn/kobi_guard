package com.kobiguard.app.repository;

import com.kobiguard.app.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, String>, PagingAndSortingRepository<Product, String> {

    List<Product> findProductsByFirmId(String firmId);
}
