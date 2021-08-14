package com.example.kampung_unite_web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.example.kampung_unite_web.model.enums.Timeslot;

import lombok.*;

import java.time.LocalTime;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class AvailableTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private GroupPlan groupPlanAT;
	private LocalTime pickupSlots;
}
