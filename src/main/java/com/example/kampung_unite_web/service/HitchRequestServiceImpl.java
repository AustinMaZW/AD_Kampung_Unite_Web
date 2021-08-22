package com.example.kampung_unite_web.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.example.kampung_unite_web.model.*;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.example.kampung_unite_web.model.enums.RequestStatus;

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


	@Transactional
	@Override
	public Boolean acceptHitchRq(int hitchRqId) {
		HitchRequest hitchRequest = hrqRepo.findHitchRequestsById(hitchRqId);
		if (hitchRequest != null) {
			hitchRequest.setRequestStatus(RequestStatus.ACCEPTED);
			hrqRepo.save(hitchRequest);            //save hrq

			GroupPlan groupPlan = hitchRequest.getGroupPlan();
			GroceryList groceryList = hitchRequest.getHitcherDetail().getGroceryList();

			groceryList.setStatus(GLStatus.ACCEPTED);
			groceryListRepository.save(groceryList);        //set the groupplan to groceryList and save

			List<GroceryItem> groceryItems = groceryList.getGroceryItems();
			List<CombinedPurchaseList> cplList = groupPlan.getCombinedPurchaseList();

			for (GroceryItem hitcherItem : groceryItems) {
				boolean cplItemAlreadyExist = false;
				for (CombinedPurchaseList cplItem : cplList) {
					if (cplItem.getProduct().getId() == hitcherItem.getProduct().getId()) {    //loop to find if cpl's product eqls hitcher's item
						cplItemAlreadyExist = true;
						int qty = cplItem.getQuantity() + hitcherItem.getQuantity();
						cplItem.setQuantity(qty);
						cplRepository.save(cplItem);        //set the new qty and save
					}
				}
				if (!cplItemAlreadyExist) {
					int qty = hitcherItem.getQuantity();
					CombinedPurchaseList newCPLItem = new CombinedPurchaseList(qty, 0, 0, groupPlan,
							hitcherItem.getProduct());
					cplRepository.save(newCPLItem);        //if no match in cpl, it means this is a new item only from hitcher, save to new row
				}

			}
			return true;
		}
		return false;
	}
	@Override
	@Transactional
	public void updateHitchRQ(HitchRequest hitchRequest){
		hrqRepo.save(hitchRequest);
	}

	@Override
	@Query
	public List<HitchRequest> findHitchRequestsByGroupPlanId(int id) {
		List<HitchRequest> hitchRequests = hrqRepo.findHitchRequestsByGroupPlanId(id);
		return hitchRequests;
	}


}
