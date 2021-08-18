package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.Product;
import com.example.kampung_unite_web.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductResource {
    @Autowired
    ProductRepository productRepository;

    @GetMapping
    public List<Product> getProductList() {
        return productRepository.findAll();
    }
}
