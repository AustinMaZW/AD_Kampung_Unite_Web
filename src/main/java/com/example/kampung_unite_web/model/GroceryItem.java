package com.example.kampung_unite_web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class GroceryItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	private double subtotal;

	@ManyToOne
	private Product product;

	@ManyToOne
	private GroceryList groceryList;

	public GroceryItem(int quantity, double subtotal, Product product, GroceryList groceryList) {
		super();
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.product = product;
		this.groceryList = groceryList;
	}

}
