package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroceryList;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
GroceryList getGroceryListByHitcherDetail_Id(int id);
}
