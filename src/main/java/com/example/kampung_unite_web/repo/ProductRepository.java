package com.example.kampung_unite_web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kampung_unite_web.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("select p from Product p where p.id IN (select c.product.id from CombinedPurchaseList c where c.groupPlan.id = :planId )")
	public List<Product> findProductsInPlan(@Param("planId") int planId);

//	@Query(value = "SELECT * FROM product p WHERE p.id IN (SELECT c.product_id FROM combined_purchase_list c WHERE c.group_plan_id = :planId)", nativeQuery = true)
//	public List<Product> findProductsInPlan(@Param("planId") int planId);
}
