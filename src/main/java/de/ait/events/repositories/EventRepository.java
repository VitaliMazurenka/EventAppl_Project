package de.ait.events.repositories;


import de.ait.events.models.Event;

public interface EventRepository extends CrudRepository<Event> {
    Event findByTitle(String title);

}
