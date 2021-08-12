package com.example.kampung_unite_web;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String description;
	private String Category;
	private String imgURL;
	
	@OneToMany(mappedBy = "productId")
	private int groceryItemId;
	
	
}
