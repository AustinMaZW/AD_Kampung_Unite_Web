package com.example.kampung_unite_web.service;

import java.time.LocalDateTime;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import java.util.List;

public interface HitchRequestService {
    public HitchRequest findHitchRQById(int HitchRqId);

    public List<HitchRequest> findHitchRQByGroceryListId(int id);

    public int saveHitcherRequest(int planId, int hitcherDetailId, LocalDateTime puckUpTime);

    // public HitchRequest findHitchRQById(int HitchRqId);
    // public List<HitchRequest> findHitchRQByGroceryListId(int id);
    public Boolean cancelHitchRq(int hitchRqId);

    public Boolean acceptHitchRq(int hitchRqId);

    public HitchRequest findHitchRQByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus);

    public void updateHitchRQ(HitchRequest hitchRequest);
}
