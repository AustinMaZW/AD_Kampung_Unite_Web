package com.example.kampung_unite_web.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kampung_unite_web.model.GroceryList;

public interface GroceryListRepository extends JpaRepository<GroceryList, Integer> {
	public List<GroceryList> findGroceryListsByUserDetailId(int id);

	public GroceryList findGroceryListById(int id);

	public List<GroceryList> findGroceryListsByGroupPlanGL_Id(int id);

	@Query("select gl from GroceryList gl where gl.hitcherDetail.id = :hitcherDetailId")
	public GroceryList findListByHitcherDetailId(@Param("hitcherDetailId") int id);
}
