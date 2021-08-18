package com.example.kampung_unite_web.model;

import lombok.*;

import java.util.List;

import javax.persistence.*;

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

	@OneToOne(mappedBy = "combinedPurchaseList")
	private GroupPlan groupPlan;

	@ManyToOne
	private Product product;
	
}
