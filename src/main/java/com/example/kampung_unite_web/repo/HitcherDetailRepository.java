package com.example.kampung_unite_web.repo;

import com.example.kampung_unite_web.model.HitchRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.kampung_unite_web.model.HitcherDetail;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HitcherDetailRepository extends JpaRepository<HitcherDetail, Integer> {
    public HitcherDetail findHitcherDetailByGroceryListId(int id);

//    @Query("SELECT rq from HitcherDetail hd where hd.hitchRequests")
//    public List<HitchRequest> findHitcherRequestByHitcherDetailId(int id);
}
