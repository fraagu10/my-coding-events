package org.launchcode.mycodingevents.controllers;

import org.launchcode.mycodingevents.data.EventData;
import org.launchcode.mycodingevents.models.Event;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("events")
public class EventController {

    @GetMapping
    public String displayAllEvents(Model model) {
        model.addAttribute("title", "All Events");
        model.addAttribute("events", EventData.getAll());

        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        // Pass in empty Event object because it has information on event fields to help render form more efficiently.
        model.addAttribute(new Event());

        return "events/create";
    }

    @PostMapping("create")
    // ModelAttribute lets us give spring the ability to create the Event object for us given the description values in the request
    // Spring Boot makes an object of type Errors available when a method uses @Valid.
    // As with Model objects, we can access this object by placing it in a method’s parameter list.
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            // If there is validations errors, we will return the form again
            return "events/create";
        }

        EventData.add(newEvent);

        return "redirect:";
    }

    // Controller method to get a view to delete events.
    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Event");
        model.addAttribute("events", EventData.getAll());

        return "events/delete";
    }

    @PostMapping("delete")
    // (required = false) parameter of @RequestParam allows the user to submit the form without any events selected.
    // Must also check the value is not null
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for(int id : eventIds) {
                EventData.remove(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("edit/{eventId}")
    public String displayEditForm(Model model, @PathVariable int eventId) {
        Event editedEvent = EventData.getById(eventId);
        model.addAttribute("event", editedEvent);
        String title = "Edit Event " + editedEvent + " (id=" + editedEvent.getId() + ")";
        model.addAttribute("title", title);

        return "events/edit";
    }

    @PostMapping("edit")
    public String processEditForm(int eventId, String name, String description, String contactEmail, String location) {
        Event editedEvent = EventData.getById(eventId);
        editedEvent.setName(name);
        editedEvent.setDescription(description);
        editedEvent.setContactEmail(contactEmail);
        editedEvent.setLocation(location);

        return "redirect:";
    }

}
