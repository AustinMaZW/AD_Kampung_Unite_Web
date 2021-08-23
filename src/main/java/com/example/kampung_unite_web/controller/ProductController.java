package com.example.kampung_unite_web.controller;

import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/productlist")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public String listProducts (Model model){
        List<Product> plist = productRepository.findAll();
        model.addAttribute("plist", plist);
        return "productcatalogue";
    }

    @GetMapping(value="/edit")
    public String editProduct (Model model){
        return "update_product";
    }
}
