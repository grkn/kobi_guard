package com.kobiguard.app.repository;


import com.kobiguard.app.entity.SelectedProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelectedProductRepository extends JpaRepository<SelectedProduct, String> {
}
