package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groceries")
public class GroceryItemResource {
    @Autowired
    GroceryItemService gItemService;


    @GetMapping(value = "/{groceryListId}")
    public List<GroceryItem> getGroceryItems(@PathVariable("groceryListId") int groceryListId){
        return gItemService.findGroceryItemsByGroceryListId(groceryListId);
    }

    @GetMapping(value = "buyer/{groupId}")
    public List<GroceryItem> getBuyerGroceryItemsByGroupId(@PathVariable("groupId") int groupId){
        return gItemService.getBuyerGroceryItemsByGroupId(groupId);
    }

    @GetMapping(value = "hitcher/{hitcherDetailId}")
    public List<GroceryItem> getGroceryItemsByHitcherDetailId(@PathVariable("hitcherDetailId") int hitcherDetailId){
        return gItemService.findGroceryItemsByGroceryList_HitcherDetail_Id(hitcherDetailId);
    }
    @RequestMapping(value="groupplan/hitcheritems", method= RequestMethod.GET)
    @ResponseBody
    public List<List<GroceryItem>> findGroceryItemsByHitchRequests(@RequestParam(value="array") List<Integer> hitchRequestIds)
    {
        return gItemService.findGroceryItemsByHitchRequests(hitchRequestIds);
    }

    @RequestMapping(value="groupplan/hitcheritems/testing/{hitchRequestId}", method= RequestMethod.GET)
    @ResponseBody
    public List<GroceryItem> findGroceryItemsByHitchRequest(@PathVariable int hitchRequestId)
    {
        return gItemService.findGroceryItemsByHitchRequest(hitchRequestId);
    }
}
