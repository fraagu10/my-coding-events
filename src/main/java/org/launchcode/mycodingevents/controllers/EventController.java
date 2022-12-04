package org.launchcode.mycodingevents.controllers;

import org.launchcode.mycodingevents.data.EventCategoryRepository;
import org.launchcode.mycodingevents.data.EventRepository;
import org.launchcode.mycodingevents.data.TagRepository;
import org.launchcode.mycodingevents.models.Event;
import org.launchcode.mycodingevents.models.EventCategory;
import org.launchcode.mycodingevents.models.Tag;
import org.launchcode.mycodingevents.models.dto.EventTagDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("events")
public class EventController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private EventCategoryRepository eventCategoryRepository;

    @Autowired
    private TagRepository tagRepository;

    @GetMapping
    public String displayAllEvents(Model model, @RequestParam(required = false) Integer categoryId) {
        // Check to see if someone passed a field in. If null then display all events like we already were.
        if(categoryId == null) {
            model.addAttribute("title", "All Events");
            model.addAttribute("events", eventRepository.findAll());
        } else {
            // A way for java to return something even when nothing is present
            Optional<EventCategory> result = eventCategoryRepository.findById(categoryId);

            if(result.isEmpty()) {
                model.addAttribute("title", "Invalid Category ID: " + categoryId);
            } else {
                EventCategory category = result.get();
                model.addAttribute("title", "Events in category: " + category.getName());
                // Here is where we are testing our new code from new field
                model.addAttribute("events", category.getEvents());
            }
        }

        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateEventForm(Model model) {
        model.addAttribute("title", "Create Event");
        model.addAttribute(new Event());
        // Refactored to use our EventCategory Repository
        model.addAttribute("categories", eventCategoryRepository.findAll());

        return "events/create";
    }

    @PostMapping("create")
    public String processCreateEventForm(@ModelAttribute @Valid Event newEvent, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Create Event");
            return "events/create";
        }

        eventRepository.save(newEvent);

        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Events");
        model.addAttribute("events", eventRepository.findAll());

        return "events/delete";
    }

    @PostMapping("delete")
    // (required = false) parameter of @RequestParam allows the user to submit the form without any events selected.
    // Must also check the value is not null
    public String processDeleteEventForm(@RequestParam(required = false) int[] eventIds) {
        if(eventIds != null) {
            for(int id : eventIds) {
                eventRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping(value = "detail")
    public String displayEventDetails(@RequestParam Integer eventId, Model model) {
        Optional<Event> result = eventRepository.findById(eventId);

        if(result.isEmpty()) {
            model.addAttribute("title", "Invalid Event ID: " + eventId);
        } else {
            Event event = result.get();
            model.addAttribute("title", event.getName() + " Details");
            model.addAttribute("event", event);
            model.addAttribute("tags", event.getTags());
        }

        return "events/detail";
    }

    // responds to /events/add-tag?eventId=13
    @GetMapping("add-tag")
    public String displayAddTagForm(@RequestParam Integer eventId, Model model){
        Optional<Event> result = eventRepository.findById(eventId);
        Event event = result.get();
        model.addAttribute("title", "Add Tag to: " + event.getName());
        model.addAttribute("tags", tagRepository.findAll());
        EventTagDTO eventTag = new EventTagDTO();
        eventTag.setEvent(event);
        model.addAttribute("eventTag", eventTag);
        return "events/add-tag.html";
    }

    @PostMapping("add-tag")
    public String processAddTagForm(@ModelAttribute @Valid EventTagDTO eventTag, Errors errors, Model model){
        if (!errors.hasErrors()) {
            Event event = eventTag.getEvent();
            Tag tag = eventTag.getTag();
            if (!event.getTags().contains(tag)){
                event.addTag(tag);
                eventRepository.save(event);
            }
            return "redirect:detail?eventId=" + event.getId();
        }

        return "redirect:add-tag";
    }

}
