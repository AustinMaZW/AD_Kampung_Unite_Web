package com.example.kampung_unite_web.repositories;

import com.example.kampung_unite_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
