package de.ait.events;


import de.ait.events.controller.EventController;
import de.ait.events.repositories.EventRepository;
import de.ait.events.repositories.impl.EventRepositoryFileImpl;
import de.ait.events.services.impl.EventServiceImpl;

import java.util.Scanner;

public class Main {

    private static final String FILE_NAME = "C:\\Users\\AIT TR Student\\Desktop\\Events_Project\\src\\main\\java\\de\\ait\\events\\events.txt";

     public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        EventRepository eventRepository = new EventRepositoryFileImpl(FILE_NAME);

        EventServiceImpl eventService = new EventServiceImpl(eventRepository); // подключаем сервис с другим репозиторием
        EventController eventController = new EventController(scanner, eventService);

        boolean isRun = true;

        while(isRun) {
            System.out.println("Input command (address): ");
            String command = scanner.nextLine();

            switch (command){
                case "/addEvent" -> eventController.addEvent();
                case "/events" -> eventController.getAllEvents();
                case "/update" -> eventController.updateEvent();
                case "/exit" -> isRun = false;
            }
        }
    }
}