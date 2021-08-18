//package com.example.kampung_unite_web.service;
//
//import com.example.kampung_unite_web.api_resource.GroceryListResource;
//import com.example.kampung_unite_web.model.GroceryItem;
//import com.example.kampung_unite_web.model.GroceryList;
//import com.example.kampung_unite_web.repo.GroceryItemRepository;
//import com.example.kampung_unite_web.repo.GroceryListRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GroceryListServiceImpl implements GroceryListService {
//    @Autowired
//    GroceryListRepository gListRepository;
//
//    @Override
//    public List<GroceryList> findAllByGroupPlanId(int groupPlanId){
//        return gListRepository.findAllByGroupPlanId(groupPlanId);
//    }
//
//}