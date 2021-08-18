package com.example.kampung_unite_web.api_resource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.example.kampung_unite_web.model.HitcherDetail;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForHitcherDetail {
	private int id;
	private LocalDate prefDate;
	private LocalTime prefPickupTimeFrom;
	private String prefPickupLocation;

	public ForHitcherDetail(LocalDate prefDate, LocalTime prefPickupTimeFrom, String prefPickupLocation) {
		super();
		this.prefDate = prefDate;
		this.prefPickupTimeFrom = prefPickupTimeFrom;
		this.prefPickupLocation = prefPickupLocation;
	}

	public HitcherDetail getHitcherDetail() {
		LocalDateTime prefPickupTimeFrom_ = LocalDateTime.of(prefDate, prefPickupTimeFrom);
		HitcherDetail detail = new HitcherDetail(prefPickupTimeFrom_, prefPickupLocation);
		return detail;

	}

}
