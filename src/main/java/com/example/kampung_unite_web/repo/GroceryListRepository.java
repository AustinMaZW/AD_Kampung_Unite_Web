package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroceryList;

public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {

    public GroceryList findGroceryListById(int id);
}
