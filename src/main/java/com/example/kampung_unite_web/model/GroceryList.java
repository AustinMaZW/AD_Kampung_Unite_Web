package com.example.kampung_unite_web.model;

import java.util.List;

import javax.persistence.*;

import com.example.kampung_unite_web.model.enums.GLStatus;
import com.example.kampung_unite_web.model.enums.HitchBuyRole;

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
public class GroceryList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private HitchBuyRole role;
	private GLStatus status;
	private String cancelReason;
	
	@OneToMany(mappedBy = "groceryList")
	private List<GroceryItem> groceries;

	@ManyToOne
	private UserDetail userDetail;

	@ManyToOne
	private GroupPlan groupPlanGL;

	@OneToOne
	private HitcherDetail hitcherDetail;
}
