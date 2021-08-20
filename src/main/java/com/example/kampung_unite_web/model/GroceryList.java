package com.example.kampung_unite_web.model;

import java.util.List;

import javax.persistence.*;

import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@JsonIgnoreProperties(value={"groceryItems"})
public class GroceryList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private HitchBuyRole role;
	private GLStatus status;
	private String cancelReason;

	@OneToMany(mappedBy = "groceryList")
	@JsonIgnoreProperties("groceryList")
	private List<GroceryItem> groceryItems;

	@ManyToOne
	@JsonIgnoreProperties("grocerylists")
	private UserDetail userDetail;

	@ManyToOne
	@JsonIgnoreProperties("groceryLists")
	private GroupPlan groupPlanGL;

	@OneToOne(cascade = CascadeType.PERSIST)
	private HitcherDetail hitcherDetail;

	public GroceryList(String name, GLStatus status, UserDetail userDetail, GroupPlan groupPlanGL, HitcherDetail hitcherDetail,
			HitchBuyRole role) {
		super();
		this.name = name;
		this.status = status;
		this.userDetail = userDetail;
		this.groupPlanGL = groupPlanGL;
		this.hitcherDetail = hitcherDetail;
		this.role = role;
	}

	public GroceryList(int id, String name, GLStatus status, UserDetail userDetail, GroupPlan groupPlanGL, HitcherDetail hitcherDetail,
					   HitchBuyRole role) {
		this.id = id;
		this.name = name;
		this.role = role;
		this.status = status;
		this.userDetail = userDetail;
		this.groupPlanGL = groupPlanGL;
		this.hitcherDetail = hitcherDetail;
	}
}
