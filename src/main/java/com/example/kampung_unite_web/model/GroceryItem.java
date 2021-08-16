package com.example.kampung_unite_web.model;

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
	private Product product;
	
	@ManyToOne
	private GroceryList groceryList;
	
}
