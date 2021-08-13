package com.example.kampung_unite_web.model.enums;

public enum GLStatus {
	COMPLETED("Completed"),
	ACCEPTED("Accepted"),
	PENDING("Pending"),
	CANCELLED("Cancelled");
	
	private final String displayGLStatus;
	
	GLStatus (String displayGLStatus){
		this.displayGLStatus = displayGLStatus;
	}
	
	public String getGLStatus() {
		return displayGLStatus;
	}
	
}
