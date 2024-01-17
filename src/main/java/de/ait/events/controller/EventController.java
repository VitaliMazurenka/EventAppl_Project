package de.ait.events.controller;

import de.ait.events.services.impl.EventServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import de.ait.events.models.Event;

public class EventController {
    private final Scanner scanner;
    private final EventServiceImpl eventService;

    public EventController(Scanner scanner, EventServiceImpl eventService) {
        this.scanner = scanner;
        this.eventService = eventService;
    }

    public void addEvent() {

        System.out.println("Input title: ");
        String title = scanner.nextLine();

        System.out.println("Input date: ");
        String dateStr = scanner.nextLine();

        LocalDate date = LocalDate.parse(dateStr);

        Event event = eventService.addEvent(title, date);

        System.out.println(event);
    }

    public void getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        System.out.println(events);
    }

    public void updateEvent() {
        System.out.println("Input ID: ");
        Long idForUpdate = scanner.nextLong();
        scanner.nextLine();

        System.out.println("Input title: ");
        String newTitle = scanner.nextLine();

        System.out.println("Input date: ");
        String str = scanner.nextLine();

        LocalDate newDate = LocalDate.parse(str);

        eventService.updateEvent(idForUpdate, newTitle, newDate);

    }
}
