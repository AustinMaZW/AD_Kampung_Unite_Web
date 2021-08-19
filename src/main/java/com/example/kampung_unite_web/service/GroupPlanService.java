package com.example.kampung_unite_web.service;

import java.util.List;

import com.example.kampung_unite_web.model.GroupPlan;

public interface GroupPlanService {

	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids);
	public Boolean quitGroceryPlan(int id);
}
