package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.service.GroceryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/groceries")
public class GroceryItemResource {
    @Autowired
    GroceryItemService gItemService;

    @GetMapping(value = "/{groceryListId}")
    public List<GroceryItem> getGroceryItemsByGroceryListId(@PathVariable("groceryListId") int groceryListId){
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

    @RequestMapping(path="/save", method = RequestMethod.GET)
    public int addGroceryItemToGroceryList (@RequestParam("productId") int productId, @RequestParam("quantity") int quantity, @RequestParam("groceryListId")  int groceryListId) {
        int groceryItemId = gItemService.addGroceryItemToGroceryList(productId, quantity, groceryListId);
        return groceryItemId;
    }

    @GetMapping(value = "group/{groupId}")
    public List<GroceryItem> getAcceptedGroceryItemsByGroupPlanId(@PathVariable("groupId") int groupId){
        return gItemService.findAcceptedGroceryItemsByGroupPlanId(groupId, GLStatus.ACCEPTED);
    }

    @PostMapping("save/all")
    public Boolean saveAll(@RequestBody List<GroceryItem> list) {
        if (list.size() > 0)
            return gItemService.saveAll(list);
        return true;
    }
}
