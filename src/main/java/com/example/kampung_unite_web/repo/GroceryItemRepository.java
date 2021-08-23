package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroceryItem;

import java.util.List;

public interface GroceryItemRepository extends JpaRepository<GroceryItem, Integer> {

    public List<GroceryItem> findGroceryItemsByGroceryListId(int id);

    List<GroceryItem> findGroceryItemsByGroceryList_HitcherDetail_Id(int id);

    public List<GroceryItem> findByGroceryListIdIn(List<Integer> ids);
}
