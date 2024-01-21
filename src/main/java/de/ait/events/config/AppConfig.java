package de.ait.events.config;


import de.ait.events.controller.EventController;
import de.ait.events.repositories.EventRepository;
import de.ait.events.repositories.impl.EventRepositoryFileImpl;
import de.ait.events.repositories.impl.EventRepositoryListImpl;
import de.ait.events.services.EventService;
import de.ait.events.services.impl.EventServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class AppConfig {
    private static final String FILE_NAME = "C:\\Users\\AIT TR Student\\Desktop\\Events_Project\\EventAppl_Project\\src\\main\\java\\de\\ait\\events\\events.txt";

    @Bean
    public EventRepository eventRepositoryList (){
        return new EventRepositoryListImpl();
    }

    @Bean
    public EventRepository eventRepositoryFile(){
        return new EventRepositoryFileImpl(FILE_NAME);
    }
    @Bean
    public EventService eventService(EventRepository eventRepositoryFile){
        return new EventServiceImpl(eventRepositoryFile);
    }
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }
//    @Bean
//    public EventController eventController(Scanner scanner, EventService eventService){
//   //     return new EventController(scanner, eventService);
//    }
}
