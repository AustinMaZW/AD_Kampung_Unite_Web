package com.example.kampung_unite_web;

import java.sql.Time;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

public class AvailableTime {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@ManyToOne
	private GroupPlan groupPlan;
	private Time time;
}
