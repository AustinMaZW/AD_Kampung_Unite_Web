package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryListServiceImpl implements GroceryListService{

    @Autowired
    GroceryListRepository glrepo;

    @Override
    public GroceryList createGroceryList(GroceryList groceryList) {
        if (groceryList == null) {
            return null;
        } else {
            return glrepo.save(groceryList);
        }
    }

    @Override
    public List<GroceryList> findGroceryListsByUserDetailId(int userDetailId) {
        return glrepo.findGroceryListsByUserDetailId(userDetailId);
    }
}
