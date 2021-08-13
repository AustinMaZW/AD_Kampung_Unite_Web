package com.example.kampung_unite_web.model.enums;

public enum HitchBuyRole {
	HITCHER("Hitcher"),
	BUYER("Buyer");
	
	private final String displayHitchBuyRole;
	
	HitchBuyRole (String displayHitchBuyRole){
		this.displayHitchBuyRole = displayHitchBuyRole;
	}
	
	public String getHitchBuyRole() {
		return displayHitchBuyRole;
	}
}
