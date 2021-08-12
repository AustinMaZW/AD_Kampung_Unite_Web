package com.example.kampung_unite_web;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class GroceryList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne 
	private int groupPlanId;
	@ManyToOne
	private User user;
	private String role;
	private String status;
	private String cancelReason;
	
	@OneToMany(mappedBy = "groceryListId")
	private List<GroceryItem> groceries;
	
}
