package com.example.kampung_unite_web.api_resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

}
