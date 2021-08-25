package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryListServiceImpl implements GroceryListService{

    @Autowired
    GroceryListRepository glrepo;
    @Autowired
    UserDetailRepository udrepo;

    @Override
    public int createGroceryListByUserDetailId(String name, int userDetailId) {
        UserDetail userDetail = udrepo.getById(userDetailId);
        if(userDetail != null) {
            GroceryList groceryList = new GroceryList();
            groceryList.setUserDetail(userDetail);
            groceryList.setName(name);
            groceryList.setRole(HitchBuyRole.HITCHER);
            glrepo.save(groceryList);
            return groceryList.getId();
        }
        else return -1;
    }

    @Override
    public List<GroceryList> findGroceryListsByUserDetailId(int userDetailId) {
        return glrepo.findGroceryListsByUserDetailId(userDetailId);
    }

    @Override
    public GroceryList findGroceryListByGroceryListId(int groceryListId) {
        return glrepo.findGroceryListById(groceryListId);
    }

    @Override
    public List<GroceryList> findGroceryListsByUserDetailIdAndRole(int userDetailId, HitchBuyRole role) {
        return glrepo.findGroceryListsByUserDetailIdAndRole(userDetailId, role);
    }


}
