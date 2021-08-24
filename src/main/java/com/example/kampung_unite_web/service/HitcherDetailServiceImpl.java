package com.example.kampung_unite_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;

@Service
public class HitcherDetailServiceImpl implements HitcherDetailService {

	@Autowired
	private HitcherDetailRepository hdrepo;
	@Autowired
	private GroceryListRepository glrepo;

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

	@Override
	public void removeDetail(int id) {
		HitcherDetail hd = hdrepo.findById(id).get();
		GroceryList gl = glrepo.findListByHitcherDetailId(hd.getId());
		gl.setHitcherDetail(null);
		glrepo.save(gl);
		hdrepo.deleteById(id);
	}
}
