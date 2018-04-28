package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addEvent(Event event) {

    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public void deleteEvent(long id) {

    }

    @Override
    public Event updateEvent(Event event) {
        return null;
    }

    @Override
    public Event getEvent(long id) {
        return null;
    }
}
