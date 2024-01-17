package de.ait.events.repositories.impl;

import de.ait.events.models.Event;
import de.ait.events.repositories.EventRepository;
import org.junit.jupiter.api.*;

import java.io.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("EventRepoFileImpl is working...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventRepositoryFileImplTest {

    private static final String TEMP_EVENTS_FILE_NAME = "events_tests.txt";

    private EventRepositoryFileImpl eventRepository;

    @BeforeEach
    public void setUp() throws IOException {
        createNewEventFileForTest(TEMP_EVENTS_FILE_NAME);
        eventRepository = new EventRepositoryFileImpl(TEMP_EVENTS_FILE_NAME);
    }

    @AfterEach
    public void tearDown() {
        deleteFileAfterTest(TEMP_EVENTS_FILE_NAME);
    }

    @DisplayName("save event")
    @Nested
    class saveTest {
        @Test
        public void write_correct_line_to_file() throws IOException {

            Event event = new Event("NewYear", LocalDate.of(2023,12,31));
            String expectedStr = "1,NewYear,2023-12-31";

            eventRepository.save(event);

            BufferedReader reader = new BufferedReader(new FileReader(TEMP_EVENTS_FILE_NAME));
            String actualStr = reader.readLine();
            assertEquals(expectedStr, actualStr);
        }

    }

    @DisplayName("find all events")
    @Nested
    class findAllTest {
        @Test
        public void returns_correct_list_of_events() throws IOException {

            BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP_EVENTS_FILE_NAME));
            writer.write("1,NewYear,2023-12-31");
            writer.newLine();
            writer.write("2,Christmas, 2024-01-07");
            writer.newLine();
            writer.close();

            List<Event> expected = Arrays.asList(
                    new Event(1L, "NewYear", LocalDate.of(2023,12,31)),
                    new Event(2L, "Christmas", LocalDate.of(2024,01,07))
            );

            List<Event> actual = eventRepository.findAll();
            assertEquals(expected, actual);
        }

    }

    private void deleteFileAfterTest(String fileName) {
        File file = new File(fileName);
        deleteIfExists(file);
    }

    private void createNewEventFileForTest(String fileName) throws IOException {
        File file = new File(fileName);

        deleteIfExists(file); // удалили, если уже есть такой

        boolean result = file.createNewFile(); // создаем новый файл
        if(!result) {
            throw new IllegalStateException("Problem with file. Can't create.");
        }
    }

    private void deleteIfExists(File file) {
        if(file.exists()){
            boolean result = file.delete();
            if(!result){
                throw new IllegalStateException("Problem with file. Can't delete.");
            }
        }
    }


}
