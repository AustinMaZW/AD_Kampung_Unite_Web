package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroceryList;

import java.util.List;


public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
    public List<GroceryList> findGroceryListsByUserDetailId(int id);
    
    public GroceryList findGroceryListById(int id);
}
