package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.Product;

import org.springframework.data.domain.Page;


import java.util.List;

public interface ProductService {
    public boolean saveProduct(Product product);

    public List<Product> findAll();

    public Product findById(int id);

    public boolean deleteProduct(Product product);

    public List<Product> searchProductByName(String name);

    public Page<Product> getAllProductsByPage(int pageNo);

}
