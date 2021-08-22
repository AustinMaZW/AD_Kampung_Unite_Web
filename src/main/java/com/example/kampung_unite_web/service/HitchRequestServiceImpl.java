package com.example.kampung_unite_web.service;

import java.time.LocalDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.example.kampung_unite_web.repo.GroupPlanRepository;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;
import com.example.kampung_unite_web.repo.HitcherRequestRepository;

@Service
public class HitchRequestServiceImpl implements HitchRequestService {
	@Autowired
	HitcherRequestRepository hrqRepo;
	@Autowired
	HitcherDetailRepository hdRepo;
	@Autowired
	GroupPlanRepository glrepo;

	@Override
	public HitchRequest findHitchRQById(int hitchRqId) {
		return hrqRepo.findHitchRequestsById(hitchRqId);
	}

	@Override
	@Transactional
	public Boolean cancelHitchRq(int hitchRqId) {
		HitchRequest hitchRequest = hrqRepo.findHitchRequestsById(hitchRqId);
		if (hitchRequest != null) {
			hrqRepo.delete(hitchRequest);
			return true;
		}
		return false;
	}

	@Override
	public HitchRequest findHitchRQByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus) {
		return hrqRepo.findHitchRequestByHitcherDetailIdAndRequestStatus(id, requestStatus);
	}

	@Override
	public List<HitchRequest> findHitchRQByGroceryListId(int groceryListId) {
		int hitcherDetailId = hdRepo.findHitcherDetailByGroceryListId(groceryListId).getId();
		List<HitchRequest> rqList = hrqRepo.findHitchRequestsByHitcherDetailId(hitcherDetailId);
		rqList.stream().forEach(x -> System.out.println(x.getId()));
		return rqList;
	}

	@Override
	public int saveHitcherRequest(int planId, int hitcherDetailId, LocalDateTime puckUpTime) {
		GroupPlan plan = glrepo.findById(planId).get();
		HitcherDetail hd = hdRepo.findById(hitcherDetailId).get();
		if (plan != null && hd != null) {
			HitchRequest request = new HitchRequest();
			request.setGroupPlan(plan);
			request.setHitcherDetail(hd);
			request.setPickupTimeChosen(puckUpTime);
			request.setRequestStatus(RequestStatus.PENDING);
			hrqRepo.save(request);
			return hrqRepo.findHitchRequestsByPlanAndDetail(planId, hitcherDetailId).getId();
		}
		return -1;
	}

	@Override
	@Transactional
	public void updateHitchRQ(HitchRequest hitchRequest) {
		hrqRepo.save(hitchRequest);
	}
}
