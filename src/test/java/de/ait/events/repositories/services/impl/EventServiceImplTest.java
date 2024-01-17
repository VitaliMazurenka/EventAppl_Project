package de.ait.events.repositories.services.impl;

import de.ait.events.models.Event;
import de.ait.events.repositories.EventRepository;
import de.ait.events.services.impl.EventServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@DisplayName("EventServiceImpl is working...")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
class EventServiceImplTest {

    private static final String EXISTED_TITLE = "New Year";
    private static final String NOT_EXISTED_TITLE = "Old New Year";
    private static final LocalDate DEFAULT_DATE = LocalDate.of(2024, 01, 01);

    private static final Event EXISTED_EVENT = new Event(EXISTED_TITLE, DEFAULT_DATE);
    private static final Event NOT_EXISTED_EVENT = new Event(NOT_EXISTED_TITLE, DEFAULT_DATE);

    private EventServiceImpl eventService;
    private EventRepository eventRepository;

    @BeforeEach
    public void setUp(){

        eventRepository = Mockito.mock(EventRepository.class);

        when(eventRepository.findByTitle(EXISTED_TITLE)).thenReturn(EXISTED_EVENT);
        when(eventRepository.findByTitle(NOT_EXISTED_TITLE)).thenReturn(null);

        this.eventService = new EventServiceImpl(eventRepository);

    }

    @Test
    public void add_event_on_incorrect_title_throws_exception(){
        assertThrows(IllegalArgumentException.class, () -> eventService.addEvent(null, DEFAULT_DATE));
    }

    @Test
    public void add_event_on_incorrect_date_throws_exception(){
        assertThrows(IllegalArgumentException.class, () -> eventService.addEvent(EXISTED_TITLE, null));
    }

       @Test
    public void on_existed_event_throw_exception (){
        assertThrows(IllegalArgumentException.class, () -> eventService.addEvent(EXISTED_TITLE, DEFAULT_DATE));
    }

    @Test
    public void return_created_user (){
        Event actual = eventService.addEvent(NOT_EXISTED_TITLE, DEFAULT_DATE);
        verify(eventRepository).save(NOT_EXISTED_EVENT);

        assertNotNull(actual); //
    }

}