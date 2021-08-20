package com.example.kampung_unite_web.service;

import java.time.LocalDateTime;
import java.util.List;

import com.example.kampung_unite_web.model.HitchRequest;

public interface HitchRequestService {
	public HitchRequest findHitchRQById(int HitchRqId);

	public List<HitchRequest> findHitchRQByGroceryListId(int id);

	public int saveHitcherRequest(int planId, int hitcherDetailId, LocalDateTime puckUpTime);
}
