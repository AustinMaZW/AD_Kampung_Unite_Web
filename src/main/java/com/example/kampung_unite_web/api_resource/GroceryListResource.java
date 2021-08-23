package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.HitchRequest;
import com.example.kampung_unite_web.service.GroceryItemService;
import com.example.kampung_unite_web.service.GroceryListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("grocerylists")
public class GroceryListResource {
    @Autowired
    GroceryListService groceryListService;


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
    public GroceryList createGroceryList(@RequestBody GroceryList groceryList) {
        if (groceryList != null) {
            groceryListService.createGroceryList(groceryList);
        } else {
            System.out.println("grocery list is null");
        }
        return groceryList;
    }

}
