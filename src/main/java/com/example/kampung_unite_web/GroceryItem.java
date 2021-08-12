package com.example.kampung_unite_web;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GroceryItem {
	@Id
	private int id;
	private int quantity;
	private double subtotal;
	
	@ManyToOne
	private int productId;
	
	@ManyToOne
	private int groceryListId;
	
}
