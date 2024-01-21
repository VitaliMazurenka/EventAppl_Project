//package de.ait.events;
//
//import de.ait.events.config.AppConfig;
//import de.ait.events.controller.EventController;
//import org.springframework.context.annotation.AnnotationConfigApplicationContext;
//
//import java.util.Scanner;
//
//public class Main1 {
//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
//        Scanner scanner = applicationContext.getBean(Scanner.class);
//        EventController eventController = applicationContext.getBean(EventController.class);
//
//        boolean isRun = true;
//
//        while (isRun) {
//            System.out.println("Input command (address): ");
//            String command = scanner.nextLine();
//
//            switch (command) {
//                case "/addEvent" -> eventController.addEvent();
//                case "/events" -> eventController.getAllEvents();
//                case "/update" -> eventController.updateEvent();
//                case "/exit" -> isRun = false;
//            }
//        }
//    }
//
//
//}
//
//
