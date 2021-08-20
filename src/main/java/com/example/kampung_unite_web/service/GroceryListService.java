package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;

import java.util.List;

public interface GroceryListService {

    GroceryList createGroceryList(GroceryList groceryList);

    List<GroceryList> findGroceryListsByUserDetailId(int userDetailId);

}
