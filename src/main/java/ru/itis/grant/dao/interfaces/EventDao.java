package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Event;

import java.util.List;

public interface EventDao {
    
    void addEvent(Event event);

    void deleteEvent(Event event);

    void deleteEvent(long id);

    Event updateEvent(Event event);

    Event getEvent(long id);

    List<Event> getUserEvents(long userId);
}
