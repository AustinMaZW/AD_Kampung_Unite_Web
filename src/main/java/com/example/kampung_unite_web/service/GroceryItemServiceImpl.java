package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.repo.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryItemServiceImpl implements GroceryItemService{
    @Autowired
    GroceryItemRepository gItemRepo;

    @Override
    public List<GroceryItem> findGroceryItemsByGroceryListId(int groceryListId){
        return gItemRepo.findGroceryItemsByGroceryListId(groceryListId);
    }
}
