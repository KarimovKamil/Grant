package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.model.Event;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Repository
public class EventDaoImpl implements EventDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addEvent(Event event) {
        em.persist(event);
    }

    @Override
    public void deleteEvent(Event event) {
        em.remove(event);
    }

    @Override
    public void deleteEvent(long id) {
        em.createQuery("delete from Event e where e.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public Event updateEvent(Event event) {
        return em.merge(event);
    }

    @Override
    public Event getEvent(long id) {
        return em.find(Event.class, id);
    }

    @Override
    public List<Event> getEvents() {
        List<Event> events = em.createQuery("from Event")
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEvents(Date date) {
        List<Event> events = em.createQuery("from Event e where e.pattern.endDate < :date")
                .setParameter("date", date)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getEventsWithPattern() {
        List<Event> events = em.createQuery("from Event e where e.pattern != null")
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEventsWithPattern(Date date) {
        List<Event> events = em.createQuery("from Event e where e.pattern != null " +
                "and e.pattern.endDate < :date")
                .setParameter("date", date)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getUserEvents(long userId) {
        List<Event> events = em.createQuery("from Event e where e.owner.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return events;
    }

    @Override
    public boolean eventExistenceById(long id) {
        return !em.createQuery("SELECT e.id FROM Event e WHERE e.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
