package com.example.kampung_unite_web.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.CombinedPurchaseList;

import java.util.List;

public interface CPLRepository extends JpaRepository<CombinedPurchaseList, Integer> {
    public List<CombinedPurchaseList> findCombinedPurchaseListsByGroupPlanId(int id);
}
