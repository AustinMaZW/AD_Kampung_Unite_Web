package com.example.kampung_unite_web.service;

import java.util.List;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.Product;

public interface GroupPlanService {

	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids);

	public List<Product> retrieveProducts(int planId);

	public Boolean quitGroceryPlan(int id);

    List<GroupPlan> findGroupPlansByUserDetailId(int userDetailId);
}
