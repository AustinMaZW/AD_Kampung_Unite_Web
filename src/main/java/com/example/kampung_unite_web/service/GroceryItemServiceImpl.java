package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.repo.GroceryItemRepository;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{
    @Autowired
    GroceryItemRepository gItemRepo;
    @Autowired
    GroceryListRepository gListRepo;
    @Autowired
    ProductRepository pRepo;

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

    @Override
    public List<GroceryItem> findAcceptedGroceryItemsByGroupPlanId(int groupId, GLStatus status) {
        List<GroceryList> groceryLists = gListRepo.findGroceryListsByGroupPlanGLIdAndStatus(groupId, status);
        List<Integer> glistIds = new ArrayList<>();
        groceryLists.stream().forEach(x->glistIds.add(x.getId()));

        return gItemRepo.findByGroceryListIdIn(glistIds);
    }

    @Override
    @Transactional
    public Boolean saveAll(List<GroceryItem> list) {
        int size = gItemRepo.saveAll(list).size();
        if (size == list.size())
            return true;
        else
            return false;
    }
    @Override
    public int addGroceryItemToGroceryList (int productId, int quantity, int groceryListId) {
        Product product = pRepo.getById(productId);
        GroceryList groceryList = gListRepo.findGroceryListById(groceryListId);
        if(product != null && groceryList != null) {
            GroceryItem groceryItem = new GroceryItem(quantity, 0, product, groceryList);
            gItemRepo.save(groceryItem);
            return groceryItem.getId();
        }
        else return -1;
    }

}
