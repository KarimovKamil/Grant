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
    public List<Event> getEvents(int from, int count) {
        List<Event> events = em.createQuery("from Event")
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEvents(Date date) {
        List<Event> events = em.createQuery("from Event e where e.endDate > :date")
                .setParameter("date", date)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEvents(Date date, int from, int count) {
        List<Event> events = em.createQuery("from Event e where e.endDate > :date")
                .setParameter("date", date)
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEventsWithPattern(Date date) {
        List<Event> events = em.createQuery("from Event e where e.pattern is not null " +
                "and e.endDate > :date and e.startDate < :date")
                .setParameter("date", date)
                .getResultList();
        return events;
    }

    @Override
    public List<Event> getActiveEventsWithPattern(Date date, int from, int count) {
        List<Event> events = em.createQuery("from Event e where e.pattern.id is not null " +
                "and e.endDate > :date and e.startDate < :date")
                .setParameter("date", date)
                .setFirstResult(from)
                .setMaxResults(count)
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
    public List<Event> getExpertEvents(String token, int from, int count) {
        List<Event> events = em.createNativeQuery("SELECT * FROM g_event WHERE id IN " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u " +
                "INNER JOIN g_user_ex_events ex ON ex.experts_id = u.id) " +
                "ORDER BY (id) LIMIT :count OFFSET :from", Event.class)
                .setParameter("token", token)
                .setParameter("from", from)
                .setParameter("count", count)
                .getResultList();
        return events;
    }

    @Override
    public boolean eventExistenceById(long id) {
        return !em.createQuery("select e.id from Event e where e.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean verifyEventPatternExistence(long eventId) {
        return !em.createQuery("select e.id from Event e where e.id = :eventId and e.pattern.id is not null")
                .setParameter("eventId", eventId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean expertEventExistence(String token, long eventId) {
        return !em.createNativeQuery("SELECT 1 FROM g_user_ex_events " +
                "WHERE ex_events_id = :eventId AND experts_id = " +
                "(SELECT id FROM g_user WHERE token = :token)")
                .setParameter("token", token)
                .setParameter("eventId", eventId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean organizerEventExistence(long eventId, String token) {
        return !em.createQuery("select e.id from Event e where e.id = :eventId " +
                "and e.owner.token = :token")
                .setParameter("eventId", eventId)
                .setParameter("token", token)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public List<Event> getOrganizerEvents(String token, int from, int count) {
        List<Event> events = em.createQuery("from Event e where e.owner.token = :token ")
                .setParameter("token", token)
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
        return events;
    }

    @Override
    public void addExpertToEvent(long eventId, long expertId) {
        em.createNativeQuery("INSERT INTO g_user_ex_events (experts_id, ex_events_id)" +
                " VALUES (:expertId, :eventId);")
                .setParameter("eventId", eventId)
                .setParameter("expertId", expertId)
                .executeUpdate();
    }

    @Override
    public void deleteExpertFromEvent(long eventId, long expertId) {
        em.createNativeQuery("DELETE FROM g_user_ex_events" +
                " WHERE (experts_id = :expertId AND ex_events_id = :eventId);")
                .setParameter("eventId", eventId)
                .setParameter("expertId", expertId)
                .executeUpdate();
    }

    @Override
    public boolean eventExpertExistence(long eventId, long expertId) {
        return !em.createNativeQuery("SELECT 1 FROM g_user_ex_events " +
                "WHERE ex_events_id = :eventId AND experts_id = :expertId")
                .setParameter("expertId", expertId)
                .setParameter("eventId", eventId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Event getEventByApplicationId(long applicationId) {
        Event event = (Event) em.createQuery("select b.pattern.event from Application b where b.id = :id")
                .setParameter("id", applicationId)
                .getSingleResult();
        return event;
    }
}
