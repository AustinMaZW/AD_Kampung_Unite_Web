package com.example.kampung_unite_web.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GroceryItem {
	@Id
	private int id;
	private int quantity;
	private double subtotal;
	
	@ManyToOne
	private Product product;
	
	@ManyToOne
	private GroceryList groceryList;
	
}
