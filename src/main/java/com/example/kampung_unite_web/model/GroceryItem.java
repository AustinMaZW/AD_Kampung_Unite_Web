package com.example.kampung_unite_web.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;

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
	//@JsonIgnoreProperties("groceryItems")		// don't think we need this, if at the end no prob then delete
	private Product product;

	@ManyToOne
	//@JsonIgnoreProperties("groceryItems")		// don't think we need this, if at the end no prob then delete
	private GroceryList groceryList;

	public GroceryItem(int quantity, double subtotal, Product product, GroceryList groceryList) {
		super();
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.product = product;
		this.groceryList = groceryList;
	}

}
