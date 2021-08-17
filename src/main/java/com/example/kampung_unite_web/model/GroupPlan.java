package com.example.kampung_unite_web.model;

import java.time.LocalDate;
import java.util.List;

import com.example.kampung_unite_web.model.enums.GroupPlanStatus;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GroupPlan {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String storeName;
	private LocalDate shoppingDate;
	private String pickupAddress;
	private LocalDate pickupDate;
	private GroupPlanStatus groupPlanStatus;

	@OneToMany(mappedBy = "groupPlan")
	private List<CombinedPurchaseList> combinedPurchaseList;

	@OneToMany(mappedBy = "groupPlanAT")
	private List<AvailableTime> availableTimes;

	@OneToMany(mappedBy = "groupPlanGL")
	private List<GroceryList> groceryLists;

	@OneToMany(mappedBy = "groupPlan")
	private List<HitchRequest> hitchRequests;

	public GroupPlan(String storeName, LocalDate shoppingDate, String pickupAddress, LocalDate pickupDate,
			GroupPlanStatus groupPlanStatus) {
		super();
		this.storeName = storeName;
		this.shoppingDate = shoppingDate;
		this.pickupAddress = pickupAddress;
		this.pickupDate = pickupDate;
		this.groupPlanStatus = groupPlanStatus;
	}


}
