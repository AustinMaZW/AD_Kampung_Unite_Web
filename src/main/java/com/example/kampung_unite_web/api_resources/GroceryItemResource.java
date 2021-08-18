package com.example.kampung_unite_web.api_resources;

import com.example.kampung_unite_web.model.GroceryItem;
import com.example.kampung_unite_web.repositories.GroceryItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("groceryitem")
public class GroceryItemResource {

    @Autowired
    GroceryItemRepository girepo;

    @GetMapping("find/{id}")
    public GroceryItem getGroceryItemById(@PathVariable("id") int id){
    return girepo.getById(id);
    }

    @GetMapping("find/all")
    public List<GroceryItem> getAllGroceryItem(){
        return girepo.findAll();
    }
}
