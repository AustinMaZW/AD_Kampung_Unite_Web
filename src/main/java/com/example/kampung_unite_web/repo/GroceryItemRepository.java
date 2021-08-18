package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroceryItem;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {

    public List<GroceryItem> findGroceryItemsByGroceryListId(int id);
}
