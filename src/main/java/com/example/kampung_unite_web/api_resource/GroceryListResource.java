package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.GroceryList;
import com.example.kampung_unite_web.model.resposeModel.StatusResponseEntity;
import com.example.kampung_unite_web.repository.GroceryListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("grocerylists")
public class GroceryListResource {
    @Autowired
    GroceryListRepository groceryListRepository;

    @GetMapping
    public List<GroceryList> getGroceryLists() {
        return groceryListRepository.findAll();
    }

    @PutMapping
    public ResponseEntity<StatusResponseEntity<GroceryList>> addGroceryList(@RequestBody GroceryList groceryList) {
        GroceryList savedItem = groceryListRepository.save(groceryList);
        StatusResponseEntity<GroceryList> statusResponseEntity = new StatusResponseEntity<GroceryList>(true, "Added new grocery list to your list",savedItem);
        return new ResponseEntity(statusResponseEntity, HttpStatus.CREATED);
    }
}

