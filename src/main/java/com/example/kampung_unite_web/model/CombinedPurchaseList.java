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
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class CombinedPurchaseList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int quantity;
	private double productSubtotal;
	private double productDiscount;
	private double productUnitPrice;
	private boolean purchasedStatus;

	public boolean isPurchasedStatus() {
		return purchasedStatus;
	}

	public void setPurchasedStatus(boolean purchasedStatus) {
		this.purchasedStatus = purchasedStatus;
	}


	@ManyToOne
	@JsonIgnoreProperties("combinedPurchaseList")
	private GroupPlan groupPlan;

	@ManyToOne
	@JsonIgnoreProperties("combinedPurchaseLists")
	private Product product;

	public CombinedPurchaseList(int quantity, double productSubtotal, double productDiscount, double productUnitPrice, GroupPlan groupPlan,
			Product product) {
		super();
		this.quantity = quantity;
		this.productSubtotal = productSubtotal;
		this.productDiscount = productDiscount;
		this.productUnitPrice = productUnitPrice;
		this.groupPlan = groupPlan;
		this.product = product;
	}

	public CombinedPurchaseList(int id, int quantity, boolean purchasedStatus) {
		this.id = id;
		this.quantity = quantity;
		this.purchasedStatus = purchasedStatus;
	}

	@Override
	public String toString() {
		return "CombinedPurchaseList{" +
				"id=" + id +
				", quantity=" + quantity +
				", productSubtotal=" + productSubtotal +
				", productUnitPrice=" + productUnitPrice +
				", purchasedStatus=" + purchasedStatus +
				", groupPlan=" + groupPlan +
				", product=" + product +
				'}';
	}
}
