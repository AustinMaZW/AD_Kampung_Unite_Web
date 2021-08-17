package com.example.kampung_unite_web.repository;

import com.example.kampung_unite_web.model.GroceryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {
}