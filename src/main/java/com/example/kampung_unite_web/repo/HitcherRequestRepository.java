package com.example.kampung_unite_web.repo;

import java.util.List;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HitcherRequestRepository extends JpaRepository<HitchRequest, Integer> {
	public List<HitchRequest> findHitchRequestsByHitcherDetailId(int id);

	public HitchRequest findHitchRequestsById(int id);

	@Query("select h from HitchRequest h where h.groupPlan.id = :planId And h.hitcherDetail.id = :detailId")
	public HitchRequest findHitchRequestsByPlanAndDetail(@Param("planId") int planId, @Param("detailId") int detailId);

	@Query("select h from HitchRequest h where h.hitcherDetail.id = :detailId")
	public List<HitchRequest> findRequestsByDetailId(@Param("detailId") int detailId);

	public HitchRequest findHitchRequestByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus);

	@Query("select h from HitchRequest h where h.groupPlan.id = :planId")
	public List<HitchRequest> findHitchRequestsByGroupPlanId(@Param("planId") int planId);

}
