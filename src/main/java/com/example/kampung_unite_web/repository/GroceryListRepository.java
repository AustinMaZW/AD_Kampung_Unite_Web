package com.example.kampung_unite_web.repository;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
    List<GroceryList> findAll();
}