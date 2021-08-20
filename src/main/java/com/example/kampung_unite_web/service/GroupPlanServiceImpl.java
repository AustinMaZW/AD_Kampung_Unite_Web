package com.example.kampung_unite_web.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.GroupPlanRepository;
import com.example.kampung_unite_web.repo.ProductRepository;

@Service
public class GroupPlanServiceImpl implements GroupPlanService {

	@Autowired
	GroupPlanRepository glrepo;
	@Autowired
	ProductRepository prepo;

	@Override
	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids) {
		List<GroupPlan> plans = new ArrayList<>();
		if (ids != null) {
			ids.stream().forEach(x -> plans.add(glrepo.findById(x).get()));
			return plans;
		}
		return null;
	}

	@Override
	public List<Product> retrieveProducts(int planId) {
		List<Product> products = prepo.findProductsInPlan(planId);
		System.out.println(planId);
		return products;
	}

}
