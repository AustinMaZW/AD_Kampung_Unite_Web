package com.example.kampung_unite_web.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.kampung_unite_web.model.*;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
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
	@Autowired
	GroceryListRepository groceryListRepository;
	@Autowired
	CPLRepository cplRepository;

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
		HitcherDetail hitcherDetail = hdRepo.findHitcherDetailByGroceryListId(groceryListId);
		if (hitcherDetail != null) {
			List<HitchRequest> rqList = hrqRepo.findHitchRequestsByHitcherDetailId(hitcherDetail.getId())
					.stream()
					.filter(x-> x.getRequestStatus()!=RequestStatus.REJECTED)			//only send back request list that aren't rejected
					.collect(Collectors.toList());

			return rqList;
		}
		return new ArrayList<>();
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

	@Transactional
	@Override
	public Boolean acceptHitchRq(int hitchRqId) {
		HitchRequest hitchRequest = hrqRepo.findHitchRequestsById(hitchRqId);
		// validation against accepted status. If alr accepted, then should not continue
		// to modify anything
		if (hitchRequest != null && hitchRequest.getRequestStatus() != RequestStatus.ACCEPTED) {
			GroupPlan groupPlan = hitchRequest.getGroupPlan();
			GroceryList groceryList = hitchRequest.getHitcherDetail().getGroceryList();

			List<HitchRequest> allHitchRqs = groceryList.getHitcherDetail().getHitchRequests();
			allHitchRqs.stream().forEach(x->{			//cancel all other hitchRq by same user since he is accepted into this one
				if (x.getId()!=hitchRqId){
					cancelHitchRq(x.getId());
				}
			});

			hitchRequest.setRequestStatus(RequestStatus.ACCEPTED);
			hrqRepo.save(hitchRequest); // save hrq



			groceryList.setStatus(GLStatus.ACCEPTED);
			groceryList.setGroupPlanGL(groupPlan);
			groceryListRepository.save(groceryList); // set the groupplan to groceryList and save

			List<GroceryItem> groceryItems = groceryList.getGroceryItems();
			List<CombinedPurchaseList> cplList = groupPlan.getCombinedPurchaseList();

			for (GroceryItem hitcherItem : groceryItems) {
				boolean cplItemAlreadyExist = false;
				for (CombinedPurchaseList cplItem : cplList) {
					if (cplItem.getProduct().getId() == hitcherItem.getProduct().getId()) { // loop to find if cpl's
																							// product eqls hitcher's
																							// item
						cplItemAlreadyExist = true;
						int qty = cplItem.getQuantity() + hitcherItem.getQuantity();
						cplItem.setQuantity(qty);
						cplRepository.save(cplItem); // set the new qty and save
					}
				}
				if (!cplItemAlreadyExist) {
					int qty = hitcherItem.getQuantity();
					CombinedPurchaseList newCPLItem = new CombinedPurchaseList(qty, 0, 0, 0, groupPlan,
							hitcherItem.getProduct());
					cplRepository.save(newCPLItem); // if no match in cpl, it means this is a new item only from
													// hitcher, save to new row
				}

			}
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public HitchRequest updateHitchRQ(HitchRequest hitchRequest) {
		return hrqRepo.save(hitchRequest);
	}

	@Override
	@Query
	public List<HitchRequest> findHitchRequestsByGroupPlanId(int id) {
		List<HitchRequest> hitchRequests = hrqRepo.findHitchRequestsByGroupPlanId(id);
		return hitchRequests;
	}
}
