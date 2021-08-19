package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;
import com.example.kampung_unite_web.repo.HitcherRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HitchRequestServiceImpl implements HitchRequestService{
    @Autowired
    HitcherRequestRepository hrqRepo;
    @Autowired
    HitcherDetailRepository hdRepo;

    @Override
    public List<HitchRequest> findHitchRQByGroceryListId(int groceryListId){
        int hitcherDetailId = hdRepo.findHitcherDetailByGroceryListId(groceryListId).getId();
        List<HitchRequest> rqList = hrqRepo.findHitchRequestsByHitcherDetailId(hitcherDetailId);
        rqList.stream().forEach(x->System.out.println(x.getId()));

        return rqList;
    }

    @Override
    public HitchRequest findHitchRQById(int hitchRqId){
        return hrqRepo.findHitchRequestsById(hitchRqId);
    }

    @Override
    public HitchRequest findHitchRQByHitcherDetailIdAndRequestStatus(int id, RequestStatus requestStatus) {
        return hrqRepo.findHitchRequestByHitcherDetailIdAndRequestStatus(id, requestStatus);
    }

}
