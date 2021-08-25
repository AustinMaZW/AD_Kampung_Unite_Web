package com.example.kampung_unite_web.service;

import com.example.kampung_unite_web.model.AvailableTime;
import com.example.kampung_unite_web.model.GroupPlan;
import com.example.kampung_unite_web.repo.GroupPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class AvailableTimeServiceImpl implements AvailableTimeService{
    @Autowired
    GroupPlanRepository gprepo;

    @Override
    public int createTimeSlotByGroupPlanId(int groupPlanId, LocalTime timeslot) {
        AvailableTime time = new AvailableTime();
        GroupPlan groupPlan = gprepo.findGroupPlanById(groupPlanId);
        time.setGroupPlanAT(groupPlan);
        time.setPickupSlots(timeslot);
        return time.getId();
    }
}
