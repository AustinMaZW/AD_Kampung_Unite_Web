package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.enums.RequestStatus;

import java.util.List;

public interface HitchRequestService {
    public HitchRequest findHitchRQById(int HitchRqId);
    public List<HitchRequest> findHitchRQByGroceryListId(int id);
    public HitchRequest findHitchRQByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus);
}
