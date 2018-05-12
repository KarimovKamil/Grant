package ru.itis.grant.dao.impl;

import org.springframework.stereotype.Repository;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.model.Bid;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BidDaoImpl implements BidDao {

    @PersistenceContext
    EntityManager em;

    @Override
    public void addBid(Bid bid) {
        em.persist(bid);
        em.flush();
    }

    @Override
    public void deleteBid(long id) {
        em.createQuery("delete from Bid b where b.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public void deleteBid(Bid bid) {
        em.remove(bid);
    }

    @Override
    public Bid updateBid(Bid bid) {
        return em.merge(bid);
    }

    @Override
    public Bid getBidById(long id) {
        return em.find(Bid.class, id);
    }

    @Override
    public List<Bid> getUserBids(long userId) {
        List<Bid> bids = em.createQuery("from Bid b where b.user.id = :userId")
                .setParameter("userId", userId)
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getUserBids(String token) {
        List<Bid> bids = em.createQuery("from Bid b where b.user.token = :token")
                .setParameter("token", token)
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getEventBids(long eventId) {
        List<Bid> bids = em.createQuery("from Bid b where b.pattern.event.id = :eventId")
                .setParameter("eventId", eventId)
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getAllBids() {
        List<Bid> bids = em.createQuery("from Bid")
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getExpertBids(String token) {
        List<Bid> bids = em.createNativeQuery("SELECT b.* FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u " +
                "INNER JOIN g_user_ex_events ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON e.ex_events_id = p.event_id " +
                "INNER JOIN bid b ON b.pattern_id = p.id WHERE b.status = 'ACTIVE'", Bid.class)
                .setParameter("token", token)
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getExpertEventBids(String token, long eventId) {
        List<Bid> bids = em.createNativeQuery("SELECT b.* FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u " +
                "INNER JOIN g_user_ex_events ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON e.ex_events_id = p.event_id " +
                "INNER JOIN bid b ON b.pattern_id = p.id WHERE e.ex_events_id = :eventId AND " +
                "b.status = 'ACTIVE'", Bid.class)
                .setParameter("token", token)
                .setParameter("eventId", eventId)
                .getResultList();
        return bids;
    }

    @Override
    public boolean bidExistenceById(long id) {
        return !em.createQuery("select b.id from Bid b where b.id = :id")
                .setParameter("id", id)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean userBidExistence(String token, long bidId) {
        return !em.createQuery("select b.id from Bid b where b.id = :bidId " +
                "and b.user.token = :token")
                .setParameter("token", token)
                .setParameter("bidId", bidId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }

    @Override
    public boolean expertBidExistence(String token, long bidId) {
        return !em.createNativeQuery("SELECT b.* FROM " +
                "(SELECT ex.ex_events_id FROM (SELECT id FROM g_user WHERE token = :token) u " +
                "INNER JOIN g_user_ex_events ex ON ex.experts_id = u.id) e " +
                "INNER JOIN pattern p ON e.ex_events_id = p.event_id " +
                "INNER JOIN bid b ON b.pattern_id = p.id WHERE b.id = :bidId AND b.status = 'ACTIVE'", Bid.class)
                .setParameter("token", token)
                .setParameter("bidId", bidId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
