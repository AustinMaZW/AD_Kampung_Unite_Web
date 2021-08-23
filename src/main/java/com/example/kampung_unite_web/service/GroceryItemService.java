package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;

import java.util.List;

public interface GroceryItemService {
    public List<GroceryItem> findGroceryItemsByGroceryListId(int groceryListId);

    List<GroceryItem> findGroceryItemsByGroceryList_HitcherDetail_Id(int hitcherDetailId);

    List<GroceryItem> getBuyerGroceryItemsByGroupId(int groupId);
}
