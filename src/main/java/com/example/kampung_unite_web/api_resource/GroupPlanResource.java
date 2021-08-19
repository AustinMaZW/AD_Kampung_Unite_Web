package com.example.kampung_unite_web.api_resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.service.GroupPlanService;

@RestController
@RequestMapping("/groupplan")
public class GroupPlanResource {

	@Autowired
	private GroupPlanService gls;

	@RequestMapping(path = "/listplans", method = RequestMethod.POST)
	public List<GroupPlan> findplans(@RequestBody List<Integer> planIds) {
		List<GroupPlan> plans = gls.findGroupPlansByListIds(planIds);
		System.out.println("shit");
		return plans;

	}

	@GetMapping(value = "/quit/{groceryListId}")
	public Boolean quitGroupPlan(@PathVariable("groceryListId") int id){
		return gls.quitGroceryPlan(id);
	}
}
