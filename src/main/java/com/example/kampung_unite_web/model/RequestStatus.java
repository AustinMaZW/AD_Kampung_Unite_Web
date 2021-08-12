package com.example.kampung_unite_web.model;

public enum RequestStatus {
    PENDING("Pending Approval"),
    ACCEPTED("Accepted"),
    REJECTED("Rejected");

    private final String displayStatus;

    RequestStatus(String displayStatus){
        this.displayStatus = displayStatus;
    }

    public String getDisplayStatus(){return displayStatus;}
}
