package com.example.kampung_unite_web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kampung_unite_web.model.HitchRequest;

public interface HitcherRequestRepository extends JpaRepository<HitchRequest, Integer> {
	public List<HitchRequest> findHitchRequestsByHitcherDetailId(int id);

	public HitchRequest findHitchRequestsById(int id);

	@Query("select h from HitchRequest h where h.groupPlan.id = :planId And h.hitcherDetail.id = :detailId")
	public HitchRequest findHitchRequestsByPlanAndDetail(@Param("planId") int planId, @Param("detailId") int detailId);
}
