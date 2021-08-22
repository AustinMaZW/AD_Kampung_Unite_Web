package com.example.kampung_unite_web.api_resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.service.HitcherDetailService;

@RestController
@RequestMapping("/HitcherDetail")
public class HitcherDetailResource {

	@Autowired
	private HitcherDetailService hds;

	@RequestMapping(path = "/saveHicherDetail", method = RequestMethod.POST)
	public int saveHitcherDetail(@RequestBody ForHitcherDetail fhd) {
		if (fhd == null) {
			return -1;
		} else {
			HitcherDetail newHd = fhd.getHitcherDetail();
			return hds.createHitcherDetail(newHd).getId();
		}
	}

	@RequestMapping(path = "/savehd", method = RequestMethod.GET)
	public int savehd(@RequestParam("pickUpdate") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate pickUpdate,
			@RequestParam("pickUptime") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime pickUptime,
			@RequestParam("address") String address) {
		if (pickUpdate == null || pickUptime == null || address == null) {
			return -1;
		} else {
			LocalDateTime pickUp = LocalDateTime.of(pickUpdate, pickUptime);
			HitcherDetail fhd = new HitcherDetail(pickUp, address);
			int id = hds.createHitcherDetail(fhd).getId();
			System.out.println(id);
			return id;
		}
	}

}
