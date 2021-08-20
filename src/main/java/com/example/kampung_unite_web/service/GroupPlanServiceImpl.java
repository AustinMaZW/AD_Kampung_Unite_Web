package com.example.kampung_unite_web.service;

import java.util.ArrayList;
import java.util.List;

import com.example.kampung_unite_web.model.*;
import com.example.kampung_unite_web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.GroupPlanRepository;
import com.example.kampung_unite_web.repo.ProductRepository;
import javax.transaction.Transactional;

@Service
public class GroupPlanServiceImpl implements GroupPlanService {

	@Autowired
	GroupPlanRepository glrepo;
	@Autowired
	ProductRepository prepo;
	@Autowired
	GroupPlanRepository gprepo;
	@Autowired
	GroceryListRepository groceryListRepo;
	@Autowired
	HitcherRequestRepository hrqRepo;
	@Autowired
	CPLRepository cplRepo;
	@Autowired
	GroceryItemRepository groceryItemRepo;

	@Override
	public List<GroupPlan> findGroupPlansByListIds(List<Integer> ids) {
		List<GroupPlan> plans = new ArrayList<>();
		if (ids != null) {
			ids.stream().forEach(x -> plans.add(gprepo.findById(x).get()));
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

	@Transactional
	@Override
	public Boolean quitGroceryPlan(int id) {
		GroceryList groceryList = groceryListRepo.findGroceryListById(id);
		if (groceryList != null) {
			List<HitchRequest> hitchRequests = hrqRepo
					.findHitchRequestsByHitcherDetailId(groceryList.getHitcherDetail().getId());
			if (hitchRequests != null) {
				hitchRequests.stream().forEach(x -> {
					if (x.getGroupPlan().getId() == groceryList.getGroupPlanGL().getId()) {
						hrqRepo.delete(x); // delete the hitchrequest associated with the groupplan
					}
				});
			}
			List<CombinedPurchaseList> combinedPurchaseList = cplRepo
					.findCombinedPurchaseListsByGroupPlanId(groceryList.getGroupPlanGL().getId());
			if (combinedPurchaseList != null) {
				List<GroceryItem> groceryItems = groceryItemRepo.findGroceryItemsByGroceryListId(groceryList.getId());
				groceryItems.stream().forEach(x -> {
					combinedPurchaseList.stream().forEach(y -> {
						if (x.getProduct().getId() == y.getProduct().getId()) { // check groceryItems that are in
																				// combinedPurchaseList
							int qty = y.getQuantity() - x.getQuantity();
							if (qty == 0) {
								cplRepo.delete(y); // delete if qty becomes 0 after deduction
							} else {
								y.setQuantity(qty); // otherwise save the new qty
								cplRepo.save(y);
							}
						}
					});
				});
			}
			groceryList.setGroupPlanGL(null); // lastly set the grocery list to not be in any groupplan
			groceryListRepo.save(groceryList);
			return true;
		}
		return false;
	}

}
