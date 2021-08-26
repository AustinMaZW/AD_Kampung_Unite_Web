package com.example.kampung_unite_web.service;

import java.time.LocalDate;
import java.util.List;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.model.enums.GroupPlanStatus;

public interface GroupPlanService {

	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids);

	public List<Product> retrieveProducts(int planId);

	public Boolean quitGroceryPlan(int id);

    List<GroupPlan> findGroupPlansByUserDetailId(int userDetailId);

	public GroupPlan findById(int id);

	public void save(GroupPlan plan);

	public GroupPlan createGroupPlan(String planName, String storeName, LocalDate shoppingDate, String pickupAddress, LocalDate pickupDate);
}
