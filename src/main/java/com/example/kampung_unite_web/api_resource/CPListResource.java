package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.service.CPListService;
import com.example.kampung_unite_web.model.CombinedPurchaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("cplist")
public class CPListResource
{
    @Autowired
    CPListService cplService;

    @GetMapping
    public List<CombinedPurchaseList> getAllLists(){
        List<CombinedPurchaseList> cplList= cplService.findAll();
        return cplList;
    }


    @GetMapping("getlist/_id={id}")
    public List<CombinedPurchaseList> getShoppingList(@PathVariable("id") int id){
        List<CombinedPurchaseList> cplList= cplService.findShoppingListByGroupPlanId(id);
        return cplList;
    }

    @PostMapping("update")
    public List<CombinedPurchaseList> updateShoppingList(@RequestBody List<CombinedPurchaseList> cplList){
        int groupPlanId = 0;
        for (CombinedPurchaseList cpl :
                cplList) {
            CombinedPurchaseList dbCPL = cplService.findCPLById(cpl.getId());
            dbCPL.setPurchasedStatus(cpl.isPurchasedStatus());
            cplService.updateCPL(dbCPL);
        }
        return cplList;
    }

    @GetMapping("groupplan/{id}")
    public List<CombinedPurchaseList> getShoppingListByGroupPlanId(@PathVariable("id") int id){
        return cplService.findShoppingListByGroupPlanId(id);
    }

    @PostMapping("save/all")
    public Boolean saveAll(@RequestBody List<CombinedPurchaseList> list) {
        if (list.size() > 0)
            return cplService.saveAll(list);
        return true;
    }
}
