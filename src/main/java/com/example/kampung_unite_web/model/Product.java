package com.example.kampung_unite_web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(value = { "groceryItems", "combinedPurchaseLists" })
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String Category;
	private String imgURL;

	@OneToMany(mappedBy = "product")
	@JsonIgnoreProperties("product")
	private List<GroceryItem> groceryItems;

	@OneToMany(mappedBy = "product")
	@JsonIgnoreProperties("product")
	private List<CombinedPurchaseList> combinedPurchaseLists;

	public Product(String name) {
		super();
		this.name = name;
	}

	public Product(String name, String imgURL){
		this.name = name;
		this.imgURL = imgURL;
	}

}
