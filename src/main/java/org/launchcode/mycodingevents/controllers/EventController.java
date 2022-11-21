package org.launchcode.mycodingevents.controllers;

import org.launchcode.mycodingevents.data.EventData;
import org.launchcode.mycodingevents.models.Event;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

        return "events/create";
    }

    @PostMapping("create")
    // ModelAttribute lets us give spring the ability to create the Event object for us given the description values in the request
    public String processCreateEventForm(@ModelAttribute Event newEvent) {
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

}
