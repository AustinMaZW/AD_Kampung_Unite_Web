package com.example.kampung_unite_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;
import com.example.kampung_unite_web.repo.HitcherRequestRepository;

@Service
public class HitcherDetailServiceImpl implements HitcherDetailService {

	@Autowired
	private HitcherDetailRepository hdrepo;
	@Autowired
	private GroceryListRepository glrepo;

	@Autowired
	private HitcherRequestRepository hrrepo;

	@Override
	public HitcherDetail createHitcherDetail(HitcherDetail hd) {
		if (hd == null) {
			return null;
		} else {
			GroceryList lis = glrepo.findById(36).get();
			lis.setHitcherDetail(hd);
			glrepo.save(lis);
			hd.setGroceryList(lis);
			return hdrepo.save(hd);
		}
	}

	public HitcherDetail createHitcherDetailWithList(HitcherDetail hd, int planId) {
		if (hd == null && planId != 0) {
			return null;
		} else {
			GroceryList lis = glrepo.findById(planId).get();
			if (lis.getHitcherDetail() != null) {
				HitcherDetail oldHd = hdrepo.findById(lis.getHitcherDetail().getId()).get();
				oldHd.setPrefPickupLocation(hd.getPrefPickupLocation());
				oldHd.setPrefPickupTimeFrom(hd.getPrefPickupTimeFrom());
				lis.setHitcherDetail(oldHd);
				glrepo.save(lis);
				return hdrepo.save(oldHd);
			} else {
				lis.setHitcherDetail(hd);
				glrepo.save(lis);
				hd.setGroceryList(lis);
				return hdrepo.save(hd);
			}

		}
	}

	@Override
	public void removeDetail(int id) {
		HitcherDetail hd = hdrepo.findById(id).get();
		GroceryList gl = glrepo.findListByHitcherDetailId(hd.getId());
		List<HitchRequest> requests = hrrepo.findRequestsByDetailId(id);
		if (gl != null) {
			System.out.println("Not null");
			if (requests.size() == 0) {
				gl.setHitcherDetail(null);
				glrepo.save(gl);
			}
		}

		if (requests.size() == 0) {
			System.out.println("delete");
			hdrepo.deleteById(id);
		}
	}
}
