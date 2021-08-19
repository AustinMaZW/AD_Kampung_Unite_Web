package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.example.kampung_unite_web.repo.HitcherDetailRepository;
import com.example.kampung_unite_web.repo.HitcherRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
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
    @Transactional
    public Boolean cancelHitchRq(int hitchRqId){
        HitchRequest hitchRequest = hrqRepo.findHitchRequestsById(hitchRqId);
        if (hitchRequest!=null){
            hrqRepo.delete(hitchRequest);
            return true;
        }
        return false;
    }

    @GetMapping(value="/accepted/{hitcherDetailId}")
    public HitchRequest getAcceptedHitchRequestByHitcherDetailId(@PathVariable("hitcherDetailId") int id) {
        HitchRequest result = hrqService.findHitchRQByHitcherDetailIdAndRequestStatus(id, RequestStatus.ACCEPTED);
        return result;
    }
}
