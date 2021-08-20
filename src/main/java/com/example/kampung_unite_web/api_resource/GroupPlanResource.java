package com.example.kampung_unite_web.api_resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.service.GroupPlanService;

@RestController
@RequestMapping("/groupplan")
public class GroupPlanResource {

	@Autowired
	private GroupPlanService gls;

	@RequestMapping(path = "/listplans", method = RequestMethod.POST)
	public List<GroupPlan> findPlans(@RequestBody List<Integer> planIds) {
		List<GroupPlan> plans = gls.findGroupPlansByListIds(planIds);
		System.out.println("Revieved findPlans: " + planIds.size());
		return plans;

	}

	@RequestMapping(path = "/retrieveproducts", method = RequestMethod.POST)
	public List<Product> findProductsInPlan(@RequestBody int planId) {
		System.out.println("Revieved findProductsInPlan: " + planId);
		List<Product> products = gls.retrieveProducts(planId);
		products.stream().forEach(x -> System.out.println(x.getId()));
		return products;
	}

	@GetMapping(value = "/quit/{groceryListId}")
	public Boolean quitGroupPlan(@PathVariable("groceryListId") int id) {
		return gls.quitGroceryPlan(id);
	}
}
