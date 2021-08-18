package com.example.kampung_unite_web.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value={"hitchRequests", "groceryList"})
public class HitcherDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDateTime prefPickupTimeFrom; // calculate pickupTimeTo with this attribute. E.g. prefPickupTime + 3
	private String prefPickupLocation;
	@OneToMany(mappedBy = "hitcherDetail")
	private List<HitchRequest> hitchRequests;
	@OneToOne(mappedBy = "hitcherDetail")
	private GroceryList groceryList;

	public HitcherDetail(LocalDateTime prefPickupTimeFrom, String prefPickupLocation) {
		super();
		this.prefPickupTimeFrom = prefPickupTimeFrom;
		this.prefPickupLocation = prefPickupLocation;
	}

}
