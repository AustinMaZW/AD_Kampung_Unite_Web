package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.GroupPlan;

public interface GroupPlanRepository extends JpaRepository<GroupPlan, Integer> {
    public GroupPlan findGroupPlanById(int id);
}