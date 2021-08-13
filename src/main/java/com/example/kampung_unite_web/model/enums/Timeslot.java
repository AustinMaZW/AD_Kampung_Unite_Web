package com.example.kampung_unite_web.model.enums;

public enum Timeslot {
	TS1("8am - 9am"),
	TS2("9am - 10am"),
	TS3("10am - 11am"),
	TS4("11am - 12pm"),
	TS5("12pm - 1pm"),
	TS6("1pm - 2pm"),
	TS7("2pm - 3pm"),
	TS8("3pm - 4pm"),
	TS9("4pm - 5pm"),
	TS10("5pm - 6pm"),
	TS11("6pm - 7pm"),
	TS12("7pm - 8pm");

	
	private final String displayTimeslot;
	
	Timeslot (String displayTimeslot){
		this.displayTimeslot = displayTimeslot;
	}
	
	public String getTimeslot() {
		return displayTimeslot;
	}
	
}