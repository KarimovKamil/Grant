package ru.itis.grant.dao.interfaces;

import ru.itis.grant.model.Bid;

import java.util.List;

public interface BidDao {

    void addBid(Bid bid);

    void deleteBid(long id);

    void deleteBid(Bid bid);

    Bid updateBid(Bid bid);

    Bid getBidById(long id);

    List<Bid> getUserBids(long userId);

    List<Bid> getUserBids(String token);

    List<Bid> getEventBids(long eventId);

    List<Bid> getAllBids();

    boolean bidExistenceById(long id);

    boolean userBidExistence(String token, long bidId);
}
