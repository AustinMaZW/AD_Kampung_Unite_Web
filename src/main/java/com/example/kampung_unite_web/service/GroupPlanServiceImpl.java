package com.example.kampung_unite_web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.repo.GroupPlanRepository;

@Service
public class GroupPlanServiceImpl implements GroupPlanService {

	@Autowired
	GroupPlanRepository glrepo;

	@Override
	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids) {
		List<GroupPlan> plans = new ArrayList<>();
		if (ids != null) {
			ids.stream().forEach(x -> plans.add(glrepo.findById(x).get()));
			return plans;
		}
		return null;
	}

}
