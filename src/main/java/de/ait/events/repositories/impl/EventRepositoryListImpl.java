package de.ait.events.repositories.impl;

import de.ait.events.models.Event;
import de.ait.events.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class EventRepositoryListImpl implements EventRepository {

    private final List<Event> events = new ArrayList<>();

    private Long generatedId = 1L;

    @Override
    public void save(Event event) {
        events.add(event);
        event.setId(generatedId);
        generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event model) {

    }

    @Override
    public Event findById(Long id) {
        return events.stream()
                .filter(event -> event.getId().longValue() == id)
                .findFirst()
                .orElse(null);

    }

    @Override
    public List<Event> findAll() {
        return new ArrayList<>(events);
    }

    @Override
    public Event findByTitle(String event) {
        return events.stream()
                .filter(e -> e.getTitle().equals(event))
                .findFirst()
                .orElse(null);
    }

}
