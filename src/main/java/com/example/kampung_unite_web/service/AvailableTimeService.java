package com.example.kampung_unite_web.service;

import java.time.LocalTime;

public interface AvailableTimeService {
    public int createTimeSlotByGroupPlanId(int groupPlanId, LocalTime timeslot);
}
