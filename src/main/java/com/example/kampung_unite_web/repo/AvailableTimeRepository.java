package com.example.kampung_unite_web.repo;

import java.util.List;

import com.example.kampung_unite_web.model.AvailableTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AvailableTimeRepository extends JpaRepository<AvailableTime, Integer> {

    @Query("select a from AvailableTime a where a.groupPlanAT.id = :planId")
    public List<AvailableTime> findSlotsByPlanId(@Param("planId") int planId);
}