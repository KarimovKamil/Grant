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
    public Bid getExpertBid(String token, long bidId) {
        Bid bid = (Bid) em.createQuery("from Bid b where (select u from User u where u.token = :token) " +
                "in b.pattern.event.experts and b.id = :id and b.status = 'ACTIVE'")
                .setParameter("token", token)
                .setParameter("id", bidId)
                .getSingleResult();
        return bid;
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
        List<Bid> bids = em.createQuery("from Bid b where (select u from User u where u.token = :token) " +
                "in b.pattern.event.experts and b.status = 'ACTIVE'")
                .setParameter("token", token)
                .getResultList();
        return bids;
    }

    @Override
    public List<Bid> getExpertEventBids(String token, long eventId) {
        List<Bid> bids = em.createQuery("from Bid b where (select u from User u where u.token = :token) " +
                "in b.pattern.event.experts and b.status = 'ACTIVE' and b.pattern.event.id = :eventId")
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
        return !em.createQuery("select b.id from Bid b where (select u from User u where u.token = :token) " +
                "in b.pattern.event.experts and b.id = :bidId and b.status = 'ACTIVE'")
                .setParameter("token", token)
                .setParameter("bidId", bidId)
                .setMaxResults(1)
                .getResultList()
                .isEmpty();
    }
}
