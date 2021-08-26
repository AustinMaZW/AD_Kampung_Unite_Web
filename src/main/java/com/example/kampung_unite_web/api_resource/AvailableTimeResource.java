package com.example.kampung_unite_web.api_resource;

import com.example.kampung_unite_web.model.AvailableTime;
import com.example.kampung_unite_web.service.AvailableTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;

@RestController
@RequestMapping("availabletime")
public class AvailableTimeResource {
    @Autowired
    AvailableTimeService availableTimeService;

    @RequestMapping(path="/save", method = RequestMethod.GET)
    public int createTimeSlotByGroupPlanId(@RequestParam("groupPlanId") int groupPlanId,
                                           @RequestParam("timeslot") @DateTimeFormat(pattern = "HH:mm:ss") LocalTime timeslot) {
        return availableTimeService.createTimeSlotByGroupPlanId(groupPlanId, timeslot);
    }
}
