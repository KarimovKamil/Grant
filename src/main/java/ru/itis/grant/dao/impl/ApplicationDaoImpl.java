package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.ApplicationDao;
import ru.itis.grant.model.Application;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ApplicationDaoImpl implements ApplicationDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addApplication(Application application) {
        em.persist(application);
        em.flush();
    }

    @Override
    public void deleteApplication(long id) {
        em.createQuery("delete from Application b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteApplication(Application application) {
        em.remove(application);
    }

    @Override
    public Application updateApplication(Application application) {
        return em.merge(application);
    }

    @Override
    public Application getApplicationById(long id) {
        return em.find(Application.class, id);
    }

    @Override
    public Application getApplicationByEventId(String token, long eventId) {
        Application application = (Application) em.createQuery("from Application b where b.pattern.event.id = :eventId " +
                "and b.user.token = :token")
                .setParameter("token", token)
                .setParameter("eventId", eventId)
                .getSingleResult();
        return application;
    }

    @Override
    public List<Application> getUserApplications(long userId) {
        List<Application> applications = em.createQuery("from Application b where b.user.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getUserApplications(String token) {
        List<Application> applications = em.createQuery("from Application b where b.user.token = :token")
                .setParameter("token", token)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getUserApplications(String token, int from, int count) {
        List<Application> applications = em.createQuery("from Application b where b.user.token = :token")
                .setParameter("token", token)
                .setFirstResult(from)
                .setMaxResults(count)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getEventApplications(long eventId) {
        List<Application> applications = em.createQuery("from Application b where b.pattern.event.id = :eventId")
                .setParameter("eventId", eventId)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getAllApplications() {
        List<Application> applications = em.createQuery("from Application")
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getExpertApplications(String token, long from, long count) {
        List<Application> applications = em.createNativeQuery("SELECT b.* FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u INNER JOIN " +
                "g_user_ex_events ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON p.event_id = e.ex_events_id " +
                "INNER JOIN (SELECT * FROM application WHERE status = 'ACTIVE') b ON b.pattern_id = p.id " +
                "ORDER BY (b.id) LIMIT :count OFFSET :from", Application.class)
                .setParameter("token", token)
                .setParameter("from", from)
                .setParameter("count", count)
                .getResultList();
        return applications;
    }

    @Override
    public List<Application> getExpertEventApplications(String token, long eventId, long from, long count) {
        List<Application> applications = em.createNativeQuery("SELECT b.* FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u INNER JOIN " +
                "(SELECT * FROM g_user_ex_events WHERE ex_events_id = :eventId) ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON p.event_id = e.ex_events_id " +
                "INNER JOIN (SELECT * FROM application WHERE status = 'ACTIVE') b ON b.pattern_id = p.id " +
                "ORDER BY (b.id) LIMIT :count OFFSET :from", Application.class)
                .setParameter("token", token)
                .setParameter("eventId", eventId)
                .setParameter("from", from)
                .setParameter("count", count)
                .getResultList();
        return applications;
    }

    @Override
    public boolean applicationExistenceById(long id) {
        return !em.createQuery("select b.id from Application b where b.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean userApplicationExistence(String token, long applicationId) {
        return !em.createQuery("select b.id from Application b where b.id = :applicationId " +
                "and b.user.token = :token")
                .setParameter("token", token)
                .setParameter("applicationId", applicationId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean userPatternApplicationExistence(String token, long patternId) {
        return !em.createQuery("select b.id from Application b where b.pattern.id = :patternId " +
                "and b.user.token = :token")
                .setParameter("token", token)
                .setParameter("patternId", patternId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean userEventApplicationExistence(String token, long eventId) {
        return !em.createQuery("select b.id from Application b where b.pattern.event.id = :eventId " +
                "and b.user.token = :token")
                .setParameter("token", token)
                .setParameter("eventId", eventId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean expertApplicationExistence(String token, long applicationId) {
        return !em.createNativeQuery("SELECT 1 FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u INNER JOIN " +
                "g_user_ex_events ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON p.event_id = e.ex_events_id " +
                "INNER JOIN (SELECT * FROM application WHERE id = :applicationId AND status = 'ACTIVE') b ON b.pattern_id = p.id")
                .setParameter("token", token)
                .setParameter("applicationId", applicationId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public Application getApplicationByEventUser(long eventId, long userId) {
        Application application = (Application) em.createQuery("from Application b where b.user.id = :userId " +
                "and b.pattern.event.id = :eventId")
                .setParameter("eventId", eventId)
                .setParameter("userId", userId)
                .getSingleResult();
        return application;
    }
}
