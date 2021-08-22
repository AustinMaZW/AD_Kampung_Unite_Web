package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.Interfaces.CPListService;
import com.example.kampung_unite_web.model.CombinedPurchaseList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("getlist/{id}")
    public List<CombinedPurchaseList> getShoppingList(@PathVariable("id") int id){
        List<CombinedPurchaseList> cplList= cplService.findShoppingListByGroupPlanId(id);
        return cplList;
    }

}
