package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.model.Bid;
import ru.itis.grant.service.interfaces.BidService;
import ru.itis.grant.validation.verification.Verification;

@Transactional
@Service
public class BidServiceImpl implements BidService {

    @Autowired
    BidDao bidDao;

    @Autowired
    Verification verification;

    @Override
    public String getBidInString(long bidId) {
        verification.verifyBidExistenceById(bidId);
        Bid bid = bidDao.getBidById(bidId);
        return bid.toString();
    }
}
