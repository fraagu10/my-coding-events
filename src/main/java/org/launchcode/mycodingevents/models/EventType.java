package org.launchcode.mycodingevents.models;

public enum EventType {

    // By placing string next to enum name, calls constructor with specific value
    CONFERENCE("Conference"),
    MEETUP("Meetup"),
    WORKSHOP("Workshop"),
    SOCIAL("Social");

    // 1. No setters since we do not want values to change.
    private final String displayName;

    EventType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
