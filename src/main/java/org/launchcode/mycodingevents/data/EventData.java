package org.launchcode.mycodingevents.data;

import org.launchcode.mycodingevents.models.Event;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class EventData {

    // Java class to store and manipulate Event data
    private static Map<Integer, Event> events = new HashMap<>();

    // Why do we use Collection class?
    public static Collection<Event> getAll() {
        return events.values();
    }

    // Add an event
    public static void add(Event event) {
        events.put(event.getId(), event);
    }

    // Find event by id
    public static Event getById(Integer id) {
        return events.get(id);
    }

    // Remove event that is specified by its id
    public static void remove(Integer id) {
        events.remove(id);
    }

}
