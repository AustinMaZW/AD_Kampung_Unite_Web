package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.Product;

import com.example.kampung_unite_web.repo.ProductPagingRepository;
import com.example.kampung_unite_web.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import com.example.kampung_unite_web.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPagingRepository productPagingRepository;

    @Override
    public boolean saveProduct(Product product){
        productRepository.save(product);
        return true;
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public Product findById(int id){
        return productRepository.findProductById(id);
    }

    @Override
    public boolean deleteProduct(Product product){
        if(product.getCombinedPurchaseLists().size()==0 && product.getGroceryItems().size()==0){
            productRepository.delete(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> searchProductByName(String name){
        return productRepository.searchProductByName(name);
    }

    @Override
    public Page<Product> getAllProductsByPage(int pageNo){
        Pageable paging = PageRequest.of(pageNo, 10, Sort.by("id").ascending());  //set default num of items to 10 here and sort by id

        return productPagingRepository.findAll(paging);

    }
}
