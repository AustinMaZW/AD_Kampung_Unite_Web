package com.example.kampung_unite_web.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.example.kampung_unite_web.model.enums.GroupPlanStatus;

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
public class GroupPlan {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int Id;
	private String storeName;
	private LocalDate shoppingDate;
	private String pickupAddress;
	private LocalDate pickupDate;
	private GroupPlanStatus groupPlanStatus;

	@OneToOne
	private CombinedPurchaseList combinedPurchaseList;

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
