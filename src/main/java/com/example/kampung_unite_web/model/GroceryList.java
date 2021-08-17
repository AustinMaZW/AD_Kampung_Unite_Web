package com.example.kampung_unite_web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
	private UserDetail userDetail;

	@ManyToOne
	private GroupPlan groupPlanGL;
}
