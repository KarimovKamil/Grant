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

    List<Event> getEvents(int from, int count);

    List<Event> getActiveEvents(Date date);

    List<Event> getActiveEvents(Date date, int from, int count);

    List<Event> getActiveEventsWithPattern(Date date);

    List<Event> getActiveEventsWithPattern(Date date, int from, int count);

    List<Event> getUserEvents(long userId);

    List<Event> getExpertEvents(String token, long from, long count);

    boolean eventExistenceById(long id);

    boolean verifyEventPatternExistence(long eventId);

    boolean verifyPatternEventExistence(long eventId);

    boolean expertEventExistence(String token, long eventId);

    boolean organizerEventExistence(long eventId, String token);

    List<Event> getOrganizerEvents(String token, long from, long count);

    void addExpertToEvent(long eventId, long expertId);

    void deleteExpertFromEvent(long eventId, long expertId);

    boolean eventExpertExistence(long eventId, long expertId);

    Event getEventByBidId(long bidId);
}
