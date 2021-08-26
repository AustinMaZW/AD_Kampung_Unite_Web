package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.service.GroceryItemService;
import com.example.kampung_unite_web.service.GroceryListService;
import com.example.kampung_unite_web.service.HitchRequestService;
import com.example.kampung_unite_web.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@RequestMapping("grocerylists")
public class GroceryListResource {
    @Autowired
    GroceryListService groceryListService;

    @Autowired
    UserDetailService userDetailService;

//    @GetMapping("/{userDetailId}")
//    public List<GroceryList> findGroceryListsByUserDetailId(@PathVariable("userDetailId") int userDetailId) {
//        return groceryListService.findGroceryListsByUserDetailId(userDetailId);
//    }

    @GetMapping("/{userDetailId}")
    public List<GroceryList> findGroceryListsByUserDetailIdAndRole(@PathVariable("userDetailId") int userDetailId) {
        return groceryListService.findGroceryListsByUserDetailIdAndRole(userDetailId, HitchBuyRole.HITCHER);
    }

    @GetMapping("/glistid/{groceryListId}")
    public GroceryList findGroceryListByGroceryListId(@PathVariable("groceryListId") int groceryListId) {
        return groceryListService.findGroceryListByGroceryListId(groceryListId);
    }

    // @PutMapping
    // public ResponseEntity<StatusResponseEntity<GroceryList>>
    // addGroceryList(@RequestBody GroceryList groceryList) {
    // GroceryList savedItem = groceryListRepository.save(groceryList);
    // StatusResponseEntity<GroceryList> statusResponseEntity = new
    // StatusResponseEntity<GroceryList>(true, "Added new grocery list to your
    // list",savedItem);
    // return new ResponseEntity(statusResponseEntity, HttpStatus.CREATED);
    // }

    @RequestMapping("new")
    public int createGroceryListByUserDetailId(@RequestParam("groceryListName") String groceryListName, @RequestParam("userDetailId") int userDetailId) {
        int groceryListId = groceryListService.createGroceryListByUserDetailId(groceryListName, userDetailId);
        return groceryListId;
    }

    @RequestMapping("update/buyerrole")
    public GroceryList updateBuyerRoleById(@RequestParam("groceryListId") int groceryListId, @RequestParam("groupPlanId") int groupPlanId) {
        return groceryListService.updateBuyerRoleById(groceryListId, groupPlanId);
    }
}

