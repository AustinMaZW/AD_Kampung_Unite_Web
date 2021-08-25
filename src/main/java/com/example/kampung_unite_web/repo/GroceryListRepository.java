package com.example.kampung_unite_web.repo;

import java.util.List;

import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;
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

    public List<GroceryList> findGroceryListsByGroupPlanGLIdAndStatus(int id, GLStatus status);

    public List<GroceryList> findGroceryListsByUserDetailIdAndRole(int id, HitchBuyRole role);
}
