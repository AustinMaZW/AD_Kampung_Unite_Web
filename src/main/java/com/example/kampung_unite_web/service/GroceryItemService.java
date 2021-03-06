package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.model.enums.GLStatus;

import java.util.List;

public interface GroceryItemService {
    public List<GroceryItem> findGroceryItemsByGroceryListId(int groceryListId);

    List<GroceryItem> findGroceryItemsByGroceryList_HitcherDetail_Id(int hitcherDetailId);

    List<GroceryItem> getBuyerGroceryItemsByGroupId(int groupId);

    public List<List<GroceryItem>> findGroceryItemsByHitchRequests(List<Integer> hitchRequestIds);

    List<GroceryItem> findGroceryItemsByHitchRequest(int hitchRequestIds);

    public List<GroceryItem> findAcceptedGroceryItemsByGroupPlanId(int groupId, GLStatus status);

    public Boolean saveAll(List<GroceryItem> list);

    public int addGroceryItemToGroceryList (int productId, int quantity, int groceryListId);

    public int updateGroceryItemInGroceryList (int groceryItemId, int quantity);
}
