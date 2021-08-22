package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
import com.example.kampung_unite_web.repo.GroceryItemRepository;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{
    @Autowired
    GroceryItemRepository gItemRepo;
    @Autowired
    GroceryListRepository gListRepo;

    @Override
    public List<GroceryItem> findGroceryItemsByGroceryListId(int groceryListId){
        return gItemRepo.findGroceryItemsByGroceryListId(groceryListId);
    }

    
    @Override
    public List<GroceryItem> findGroceryItemsByGroceryList_HitcherDetail_Id(int hitcherDetailId) {
        return gItemRepo.findGroceryItemsByGroceryList_HitcherDetail_Id(hitcherDetailId);
    }

    @Override
    public List<GroceryItem> getBuyerGroceryItemsByGroupId(int groupId) {
        List<GroceryList> groceryLists = gListRepo.findGroceryListsByGroupPlanGL_Id(groupId);
        GroceryList buyerGroceryList = groceryLists
                .stream()
                .filter(x->x.getRole().equals(HitchBuyRole.BUYER))
                .findAny().get();
        List<GroceryItem> groceryItems = gItemRepo.findGroceryItemsByGroceryListId(buyerGroceryList.getId());

        return groceryItems;
    }

}
