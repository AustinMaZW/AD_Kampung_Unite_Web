package com.example.kampung_unite_web.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.kampung_unite_web.model.*;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.GroupPlanStatus;
import com.example.kampung_unite_web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	AvailableTimeRepository arepo;

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
					.findHitchRequestsByHitcherDetailId(groceryList.getHitcherDetail().getId()); // because i am sending
																									// grocerylist to
																									// server, need to
																									// find the hitchrq
																									// associated with
																									// groupplan...
																									// alternatively
																									// could pass
																									// hitchrq
			if (hitchRequests != null) {
				hitchRequests.stream().forEach(x -> {
					if (x.getGroupPlan().getId() == groceryList.getGroupPlanGL().getId()) {
						hrqRepo.delete(x); // delete the hitchrequest associated with the groupplan
					}
				});
			}
			List<CombinedPurchaseList> combinedPurchaseList = cplRepo
					.findCombinedPurchaseListsByGroupPlanId(groceryList.getGroupPlanGL().getId()); // get list of cpl so
																									// we can remove
																									// items from
																									// hitcher's list
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

	@Override
	public List<GroupPlan> findGroupPlansByUserDetailId(int userDetailId) {
		List<GroupPlan> groupPlans = gprepo.findAll();
		List<GroupPlan> mgroupPlans = groupPlans.stream()
				.filter(x -> x.getGroceryLists().stream().anyMatch(y -> y.getUserDetail().getId() == userDetailId))
				.collect(Collectors.toList());
		return mgroupPlans;
	}

	@Override
	public GroupPlan findById(int id) {
		return gprepo.findById(id).get();
	}

	@Override
	public void save(GroupPlan plan) {
		gprepo.save(plan);
	}

	@Override
	public GroupPlan createGroupPlan(String planName, String storeName, LocalDate shoppingDate, String pickupAddress,
									 LocalDate pickupDate, LocalTime time1, LocalTime time2, LocalTime time3) {
		GroupPlan groupPlan = new GroupPlan();
		groupPlan.setPlanName(planName);
		groupPlan.setStoreName(storeName);
		groupPlan.setShoppingDate(shoppingDate);
		groupPlan.setPickupAddress(pickupAddress);
		groupPlan.setPickupDate(pickupDate);
		groupPlan.setGroupPlanStatus(GroupPlanStatus.AVAILABLE);
		glrepo.save(groupPlan);

		LocalTime[] times = {time1,time2,time3};
		for (LocalTime time:times) {
			if(time != null) {
				AvailableTime availableTime = new AvailableTime();
				availableTime.setGroupPlanAT(groupPlan);
				availableTime.setPickupSlots(time);
				arepo.save(availableTime);
			}
		}
		return groupPlan;
	}

	@Override
	public List<String> findSlotsByplan(int planId) {
		List<AvailableTime> slots = arepo.findSlotsByPlanId(planId);
		DateTimeFormatter df = DateTimeFormatter.ISO_TIME;
		List<String> strSlots = slots.stream().map(x -> df.format(x.getPickupSlots())).collect(Collectors.toList());
		return strSlots;
	}

	@Override
	public Map<Integer, List<String>> findSlotsByPlanIds(List<Integer> planIds) {
		Map<Integer, List<String>> slots = new HashMap<>();
		if (planIds != null && planIds.size() > 0) {
			planIds.stream().forEach(x -> slots.put(x, findSlotsByplan(x)));
			return slots;
		}
		return null;
	}
}
