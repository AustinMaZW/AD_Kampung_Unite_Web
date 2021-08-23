package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.Interfaces.UserDetailService;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.model.HitcherDetail;
import com.example.kampung_unite_web.model.UserDetail;
import com.example.kampung_unite_web.model.resposeModel.StatusResponseEntity;
import com.example.kampung_unite_web.repo.GroceryListRepository;
import com.example.kampung_unite_web.service.GroceryListService;
import com.example.kampung_unite_web.service.HitchRequestService;
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

    @GetMapping("/{userDetailId}")
    public List<GroceryList> findGroceryListsByUserDetailId(@PathVariable("userDetailId") int userDetailId) {
        return groceryListService.findGroceryListsByUserDetailId(userDetailId);
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

    @PostMapping("new")
    public GroceryList createGroceryListByUserDetailId(@RequestBody GroceryList groceryList, @PathVariable("userDetailId") int userDetailId) {
        if(groceryList != null) {
            groceryList.setUserDetail(userDetailService.findUserById(userDetailId));
            groceryListService.createGroceryList(groceryList);
            System.out.println("new grocery list created for user " + userDetailService.findUserById(userDetailId).getFirstName());
        } else {
            System.out.println("grocery list is null");
        }
        return groceryList;
    }
}

