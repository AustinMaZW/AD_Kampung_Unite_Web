package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.Product;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ProductPagingRepository extends PagingAndSortingRepository<Product, Integer> {
}
