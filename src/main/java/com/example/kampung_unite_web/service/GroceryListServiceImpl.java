package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.*;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
import com.example.kampung_unite_web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroceryListServiceImpl implements GroceryListService{

    @Autowired
    GroceryListRepository glrepo;
    @Autowired
    UserDetailRepository udrepo;
    @Autowired
    GroupPlanRepository gprepo;
    @Autowired
    CPLRepository cplrepo;
    @Autowired
    GroceryItemRepository girepo;

    @Override
    public int createGroceryListByUserDetailId(String name, int userDetailId) {
        UserDetail userDetail = udrepo.getById(userDetailId);
        if(userDetail != null) {
            GroceryList groceryList = new GroceryList();
            groceryList.setUserDetail(userDetail);
            groceryList.setName(name);
            groceryList.setStatus(GLStatus.PENDING);
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

    @Override
    public GroceryList updateBuyerRoleById(int groceryListId, int groupPlanId) {
        GroceryList groceryList = glrepo.findGroceryListById(groceryListId);
        GroupPlan groupPlan = gprepo.findGroupPlanById(groupPlanId);
        List<GroceryItem> groceryItems = girepo.findGroceryItemsByGroceryListId(groceryListId);

        for (GroceryItem item:groceryItems) {
            CombinedPurchaseList combinedPurchaseList = new CombinedPurchaseList();
            combinedPurchaseList.setGroupPlan(groupPlan);
            combinedPurchaseList.setProduct(item.getProduct());
            combinedPurchaseList.setQuantity(item.getQuantity());
            cplrepo.save(combinedPurchaseList);

        }

        groceryList.setRole(HitchBuyRole.BUYER);
        groceryList.setGroupPlanGL(groupPlan);

        List<GroceryList> groceryLists = new ArrayList<>();
        groceryLists.add(groceryList);
        groupPlan.setGroceryLists(groceryLists);
        glrepo.save(groceryList);
        gprepo.save(groupPlan);
        return groceryList;
    }
}
