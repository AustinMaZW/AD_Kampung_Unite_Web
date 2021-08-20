package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitcherRequestRepository extends JpaRepository<HitchRequest, Integer> {
    public List<HitchRequest> findHitchRequestsByHitcherDetailId(int id);

    public HitchRequest findHitchRequestsById(int id);

    public HitchRequest findHitchRequestByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus);
}
