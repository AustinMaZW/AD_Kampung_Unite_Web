package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;

import java.util.List;

public interface GroceryListService {

    public int createGroceryListByUserDetailId(String name, int userDetailId);

    public List<GroceryList> findGroceryListsByUserDetailId(int userDetailId);

    public GroceryList findGroceryListByGroceryListId(int groceryListId);

    public List<GroceryList> findGroceryListsByUserDetailIdAndRole(int id, HitchBuyRole role);

}
