package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.HitcherDetail;

public interface HitcherDetailService {
	public HitcherDetail createHitcherDetailWithList(HitcherDetail hd, int planId);

	public HitcherDetail createHitcherDetail(HitcherDetail hd);

	public void removeDetail(int id);
}
