package com.example.kampung_unite_web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
