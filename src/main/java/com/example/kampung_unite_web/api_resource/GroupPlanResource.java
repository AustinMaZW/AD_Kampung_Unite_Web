package com.example.kampung_unite_web.api_resource;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.example.kampung_unite_web.model.enums.GroupPlanStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import com.example.kampung_unite_web.model.AvailableTime;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.service.GroceryListService;
import com.example.kampung_unite_web.service.GroupPlanService;

@RestController
@RequestMapping("/groupplan")
public class GroupPlanResource {
	@Autowired
	private GroceryListService gs;
	@Autowired
	private GroupPlanService gls;

	@GetMapping("/{userDetailId}")
	public List<GroupPlan> findGroupPlansByUserDetailId(@PathVariable("userDetailId") int userDetailId) {
		return gls.findGroupPlansByUserDetailId(userDetailId);
	}

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

	@GetMapping("/update/status/{id}/{status}")
	public void updateStatus(@PathVariable("id") int id, @PathVariable("status") GroupPlanStatus status) {
		GroupPlan plan = gls.findById(id);
		plan.setGroupPlanStatus(status);
		gls.save(plan);
	}

	@GetMapping("/plan/{id}")
	public GroupPlan getGroupPlanById(@PathVariable("id") int id) {
		return gls.findById(id);
	}

	@RequestMapping(path = "/save", method = RequestMethod.GET)
	public GroupPlan createGroupPlan(@RequestParam("planName") String planName,
			@RequestParam("storeName") String storeName,
			@RequestParam("shoppingDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate shoppingDate,
			@RequestParam("pickUpAddress") String pickupAddress,
			@RequestParam("pickUpDate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pickupDate,
			@RequestParam("time1") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time1,
			@RequestParam("time2") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time2,
			@RequestParam("time3") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime time3) {
		GroupPlan groupPlan = gls.createGroupPlan(planName, storeName, shoppingDate, pickupAddress, pickupDate, time1,
				time2, time3);
		return groupPlan;
	}

	@RequestMapping(path = "/availableTime/{planId}", method = RequestMethod.POST)
	public List<String> findPickSlots(@PathVariable int planId) {

		return gls.findSlotsByplan(planId);

	}

	@RequestMapping(path = "/availableTimes", method = RequestMethod.POST)
	public Map<Integer, List<String>> findSlots(@RequestBody List<Integer> planIds) {

		return gls.findSlotsByPlanIds(planIds);
	}

	@RequestMapping(path = "/findHd/{listId}", method = RequestMethod.GET)
	public HitcherDetail findHitcherDetailByList(@PathVariable("listId") int listId) {
		GroceryList gList = gs.findGroceryListByGroceryListId(listId);
		if (gList.getHitcherDetail() != null && gList.getHitcherDetail().getPrefPickupLocation() != null
				&& gList.getHitcherDetail().getPrefPickupLocation().length() > 0) {
			HitcherDetail hd = gList.getHitcherDetail();
			System.out.println(hd.getPrefPickupLocation());
			return hd;
		} else {
			HitcherDetail hd = new HitcherDetail();
			hd.setPrefPickupLocation("not available");
			return hd;
		}
	}
}
