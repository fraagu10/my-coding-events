package org.launchcode.mycodingevents.models;

import javax.validation.constraints.*;
import java.util.Objects;

// Validation must take place in both the model AND controller.
public class Event {

    private int id;
    private static int nextId = 1;
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters.")
    @NotBlank(message = "Name is required.")
    private String name;
    @Size(max = 270, message = "Description too long.")
    private String description;
    @Email(message = "Invalid email. Try again.")
    @NotBlank(message = "Email is required.")
    private String contactEmail;
    @NotBlank(message = "Please enter an address.")
    @NotNull
    private String location;
    @NotNull
    private boolean registrationRequired;
    @Positive(message = "Number of attendees must be greater than 0.")
    private int numberOfAttendees;

    public Event(String name, String description, String contactEmail, String address,
                 boolean registrationRequired, int numberOfAttendees) {
        this();
        this.name = name;
        this.description = description;
        this.contactEmail = contactEmail;
        this.location = address;
        this.registrationRequired = registrationRequired;
        this.numberOfAttendees = numberOfAttendees;
    }

    public Event() {
        this.id = nextId;
        nextId++;
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isRegistrationRequired() {
        return registrationRequired;
    }

    public void setRegistrationRequired(boolean registrationRequired) {
        this.registrationRequired = registrationRequired;
    }

    public int getNumberOfAttendees() {
        return numberOfAttendees;
    }

    public void setNumberOfAttendees(int numberOfAttendees) {
        this.numberOfAttendees = numberOfAttendees;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return id == event.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
