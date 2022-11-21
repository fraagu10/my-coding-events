package org.launchcode.mycodingevents.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("events")
public class EventController {

    private static HashMap<String, String> events = new HashMap<>();

    @GetMapping
    public String displayAllEvents(Model model) {
        events.put("Launchcode", "Intro web development program");
        events.put("Scrimmige", "The place to practice cool soccer tricks");
        events.put("Javascripty", "An imaginary meetup for Javascript developers");
        events.put("Menteaship","A fun meetup for connecting with mentors");

        model.addAttribute("events", events);
        return "events/index";
    }

    @GetMapping("create")
    public String displayCreateForm() {
        return "events/create";
    }

    @PostMapping("create")
    public String processCreateForm(@RequestParam String eventName, Model model) {
        //events.add(eventName);
        return "redirect:";
    }

}
