package org.launchcode.mycodingevents.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

// Entity classes have two constructors.
@Entity
public class Event extends AbstractEntity {
    // Set up object based relationship. Think of the type of relationship that is here.
    // In our case, our Event can only be in one category.
    @ManyToOne // Many events in one category
    @NotNull(message = "Category is required")
    private EventCategory eventCategory;

    // Cascade - allows to specify in relationship between two objects how ORM operations are applied to sub objects
    @OneToOne(cascade = CascadeType.ALL)
    @Valid // When we want to validate inside EventDetails object
    @NotNull // This will make sure field is not empty on surface
    private EventDetails eventDetails;

    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;

    public Event(String name, EventCategory eventCategory) {
        this.name = name;
        this.eventCategory = eventCategory;
    }

    // Constructor used by JPA
    public Event() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventCategory getEventCategory() {
        return eventCategory;
    }

    public void setEventCategory(EventCategory eventCategory) {
        this.eventCategory = eventCategory;
    }

    public EventDetails getEventDetails() {
        return eventDetails;
    }

    public void setEventDetails(EventDetails eventDetails) {
        this.eventDetails = eventDetails;
    }

    @Override
    public String toString() {
        return name;
    }
}