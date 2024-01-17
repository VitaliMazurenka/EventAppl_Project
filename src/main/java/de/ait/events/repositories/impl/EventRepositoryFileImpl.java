package de.ait.events.repositories.impl;

import de.ait.events.models.Event;
import de.ait.events.repositories.EventRepository;


import java.io.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class EventRepositoryFileImpl implements EventRepository {
    private final String fileName; // DI
    private Long generatedId = 1L;

    public EventRepositoryFileImpl(String fileName) {
        this.fileName = fileName;
    }


    @Override
    public void save(Event event) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            event.setId(findAll().size() + generatedId); // получаем размер списка пользователей и вычисляем id
            writer.write(event.getId() + "," + event.getTitle() + "," + event.getDate());
            writer.newLine();
            writer.flush();

        } catch (IOException e) {
            throw new IllegalStateException("Problem with file." + e.getMessage());
        }
        // generatedId++;
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(Event event) {

        // на вход получили событие, нужно его данные перезаписать в файле.
        // Файл перезаписывается полностью!
        List<Event> events = findAll();

        Event eventForUpdate = events.stream()
                .filter(e -> e.getId() == event.getId())
                .findFirst()
                .orElse(null);

        events.remove(eventForUpdate);

        events.add(event);

        try (
                BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Event e : events) {
                writer.write(e.getId() + "," + e.getTitle() + "," + e.getDate());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new IllegalStateException("Problem with file: " + e.getMessage());
        }
   }
    @Override
    public Event findById(Long id) {
        return null;
    }

    @Override
    public List<Event> findAll() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(","))
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1], LocalDate.parse(parsed[2])))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            throw new IllegalStateException("Problem with file.");
        }
    }

    @Override
    public Event findByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            return reader.lines()
                    .map(line -> line.split(","))
                    .filter(parsed -> parsed[1].contains(title)) // совпадение по подстроке
                    .findFirst()
                    .map(parsed -> new Event(Long.parseLong(parsed[0]), parsed[1], LocalDate.parse(parsed[2])))
                    .orElse(null);

        } catch (IOException e) {
            throw new IllegalStateException("Problem with file.");
        }
    }

}
