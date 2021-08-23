package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.CombinedPurchaseList;

import java.util.List;

public interface CPListService {

    List<CombinedPurchaseList> findAll();

    List<CombinedPurchaseList> findShoppingListByGroupPlanId(int id);

    CombinedPurchaseList findCPLById(int id);

    void updateCPL(CombinedPurchaseList combinedPurchaseList);

    void updateList(List<CombinedPurchaseList> cplList);
}
