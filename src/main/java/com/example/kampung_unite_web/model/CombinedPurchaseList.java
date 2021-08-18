package com.example.kampung_unite_web.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
public class CombinedPurchaseList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	private double productSubtotal;
	private double productUnitPrice;

	@ManyToOne
	@JsonIgnoreProperties("combinedPurchaseList")
	private GroupPlan groupPlan;

	@ManyToOne
	@JsonIgnoreProperties("combinedPurchaseLists")
	private Product product;

	public CombinedPurchaseList(int quantity, double productSubtotal, double productUnitPrice, GroupPlan groupPlan,
			Product product) {
		super();
		this.quantity = quantity;
		this.productSubtotal = productSubtotal;
		this.productUnitPrice = productUnitPrice;
		this.groupPlan = groupPlan;
		this.product = product;
	}

}
