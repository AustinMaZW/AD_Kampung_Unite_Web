package com.example.kampung_unite_web.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class CombinedPurchaseList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	private int productId;
	private int quantity;
	private double productSubtotal;
	private double productUnitPrice;

	@OneToOne(mappedBy = "combinedPurchaseList")
	private GroupPlan groupPlan;
	
}
