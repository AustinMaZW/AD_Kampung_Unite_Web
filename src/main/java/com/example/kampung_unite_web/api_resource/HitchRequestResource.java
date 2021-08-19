package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.enums.RequestStatus;
import com.example.kampung_unite_web.service.HitchRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hitchrequest")
public class HitchRequestResource {
    @Autowired
    HitchRequestService hrqService;

    @GetMapping(value = "/{groceryListId}")
    public List<HitchRequest> getHitchRqs(@PathVariable("groceryListId") int groceryListId){
        return hrqService.findHitchRQByGroceryListId(groceryListId);
    }

    @GetMapping(value = "/hrq/{id}")
    public HitchRequest getHitchRq(@PathVariable("id") int hrqId){
        return hrqService.findHitchRQById(hrqId);
    }

    @GetMapping(value = "cancel/{hitchRequestId}")
    public Boolean cancelHitchRq(@PathVariable("hitchRequestId") int hitchRequestId){
        return hrqService.cancelHitchRq(hitchRequestId);
    }

    @GetMapping(value="/accepted/{hitcherDetailId}")
    public HitchRequest getAcceptedHitchRequestByHitcherDetailId(@PathVariable("hitcherDetailId") int id) {
        HitchRequest result = hrqService.findHitchRQByHitcherDetailIdAndRequestStatus(id, RequestStatus.ACCEPTED);
        return result;
    }
}
