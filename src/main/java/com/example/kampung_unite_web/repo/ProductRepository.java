package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
