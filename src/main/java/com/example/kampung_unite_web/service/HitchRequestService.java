package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.HitchRequest;

import java.util.List;

public interface HitchRequestService {
    public HitchRequest findHitchRQById(int HitchRqId);
    public List<HitchRequest> findHitchRQByGroceryListId(int id);
}
