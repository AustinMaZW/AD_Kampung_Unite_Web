package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.GroceryList;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroupPlan;

import java.util.List;

public interface GroupPlanRepository extends JpaRepository<GroupPlan, Integer> {
}
