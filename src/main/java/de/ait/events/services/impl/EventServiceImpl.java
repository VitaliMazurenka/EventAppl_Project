package de.ait.events.services.impl;


import de.ait.events.models.Event;
import de.ait.events.repositories.EventRepository;
import de.ait.events.services.EventService;

import java.time.LocalDate;
import java.util.List;

public class EventServiceImpl implements EventService {
    @Override
    public Event addEvent(String title, LocalDate date) {
        if (title == null || title ==
                "" || title.equals(" ")) {
            throw new IllegalArgumentException("Event can't be empty!");
        }
        if (date == null) {
            throw new IllegalArgumentException("Date can't be empty!");
        }
        Event existedEvent = eventRepository.findByTitle(title);
        if (existedEvent != null) {
            throw new IllegalArgumentException("Event is already exist!");
        }
        Event event = new Event(title,date);
        eventRepository.save(event);
        return event;
    }
    public final EventRepository eventRepository;

    public EventServiceImpl(EventRepository userRepository) {
        this.eventRepository = userRepository;
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

       public void updateEvent(Long id, String newTitle, LocalDate newDate) {
        List<Event> events = eventRepository.findAll();
        Event eventForUpdate = events.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .orElse(null);

        if (eventForUpdate == null) {
            throw new IllegalArgumentException("Event with id <" + id + ">is not found");
        }
        if (!newTitle.isBlank()) {
            eventForUpdate.setTitle(newTitle);
        }
        if (!(newDate==null)) {
            eventForUpdate.setDate(newDate);
        }
        eventRepository.update(eventForUpdate);
    }

}
