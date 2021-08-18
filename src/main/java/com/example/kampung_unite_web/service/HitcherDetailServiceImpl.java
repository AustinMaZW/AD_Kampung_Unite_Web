package com.example.kampung_unite_web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;

@Service
public class HitcherDetailServiceImpl implements HitcherDetailService {

	@Autowired
	private HitcherDetailRepository hdrepo;

	@Override
	public boolean createHitcherDetail(HitcherDetail hd) {
		if (hd == null) {
			return false;
		} else {
			hdrepo.save(hd);
			return true;
		}
	}
}
