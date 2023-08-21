package com.alaindroid.sportsbet.model;

public enum TicketType {
    ADULT("Adult"), SENIOR("Senior"), TEEN("Teen"), CHILDREN("Children");
    public String name;

    TicketType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
