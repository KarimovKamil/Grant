package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Event;

import java.util.Date;
import java.util.List;

public interface EventDao {

    void addEvent(Event event);

    void deleteEvent(Event event);

    void deleteEvent(long id);

    Event updateEvent(Event event);

    Event getEvent(long id);

    List<Event> getEvents();

    List<Event> getActiveEvents(Date date);

    List<Event> getEventsWithPattern();

    List<Event> getActiveEventsWithPattern(Date date);

    List<Event> getUserEvents(long userId);

    List<Event> getExpertEvents(String token);

    boolean eventExistenceById(long id);

    boolean verifyEventPatternExistence(long eventId);

    boolean expertEventExistence(String token, long eventId);

    boolean organizerEventExistence(long eventId, String token);

    List<Event> getOrganizerEvents(String token);

    void addExpertToEvent(long eventId, long expertId);

    boolean eventExpertExistence(long eventId, long expertId);
}
