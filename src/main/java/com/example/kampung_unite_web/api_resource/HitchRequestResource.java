package com.example.kampung_unite_web.api_resource;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.service.HitchRequestService;

@RestController
@RequestMapping("/hitchrequest")
public class HitchRequestResource {
	@Autowired
	HitchRequestService hrqService;

	@GetMapping(value = "/{groceryListId}")
	public List<HitchRequest> getHitchRqs(@PathVariable("groceryListId") int groceryListId) {
		return hrqService.findHitchRQByGroceryListId(groceryListId);
	}

	@GetMapping(value = "/hrq/{id}")
	public HitchRequest getHitchRq(@PathVariable("id") int hrqId) {
		return hrqService.findHitchRQById(hrqId);
	}

	@RequestMapping(path = "/savereq", method = RequestMethod.GET)
	public int saveRequest(@RequestParam("planId") int planId, @RequestParam("hitcherDetailId") int hitcherDetailId,
			@RequestParam("pickUpTime") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime pickUpTime) {
		int requestId = hrqService.saveHitcherRequest(planId, hitcherDetailId, pickUpTime);
		System.out.println(requestId);
		return requestId;
	}
}
