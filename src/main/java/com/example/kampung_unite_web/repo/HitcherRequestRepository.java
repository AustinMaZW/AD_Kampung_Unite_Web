package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.HitchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HitcherRequestRepository extends JpaRepository<HitchRequest, Integer> {
    public List<HitchRequest> findHitchRequestsByHitcherDetailId(int id);

    public HitchRequest findHitchRequestsById(int id);

    public List<HitchRequest> findHitchRequestsByGroupPlanId(int id);
}
