package com.example.kampung_unite_web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
	@JsonIgnoreProperties("groceryItems")
	private Product product;

	@ManyToOne
	@JsonIgnoreProperties("groceryItems")
	private GroceryList groceryList;

	public GroceryItem(int quantity, double subtotal, Product product, GroceryList groceryList) {
		super();
		this.quantity = quantity;
		this.subtotal = subtotal;
		this.product = product;
		this.groceryList = groceryList;
	}

}
