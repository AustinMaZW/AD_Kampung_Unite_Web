package com.example.kampung_unite_web.model.enums;

public enum GroupPlanStatus {
    AVAILABLE("Available"),
    SHOPPINGCOMPLETED("Shopping Completed"),
    CLOSED("Closed"),
    CANCELLED("Cancelled");

    private final String displayStatus;

    GroupPlanStatus(String displayStatus){
        this.displayStatus = displayStatus;
    }

    public String getDisplayStatus(){return displayStatus;}
}
