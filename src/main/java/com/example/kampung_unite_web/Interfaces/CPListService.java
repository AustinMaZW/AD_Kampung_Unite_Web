package com.example.kampung_unite_web.Interfaces;

import com.example.kampung_unite_web.model.CombinedPurchaseList;

import java.util.List;

public interface CPListService
{
    List<CombinedPurchaseList> findAll();
    List<CombinedPurchaseList> findShoppingListByGroupPlanId(int groupPlanId);
    boolean saveAll(List<CombinedPurchaseList> list);
}
