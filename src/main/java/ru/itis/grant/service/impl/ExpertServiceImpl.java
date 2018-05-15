package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBanDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.model.Ban;
import ru.itis.grant.model.Bid;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.User;
import ru.itis.grant.service.interfaces.ExpertService;
import ru.itis.grant.validation.verification.Verification;

import java.util.List;

@Transactional
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    UserDao userDao;
    @Autowired
    BidDao bidDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    ConversionResultFactory conversionResultFactory;
    @Autowired
    ConversionListResultFactory conversionListResultFactory;
    @Autowired
    Verification verification;

    @Override
    public List<ResponseEventDto> getExpertEvents(String token) {
        verification.verifyTokenExistence(token);
        List<Event> events = eventDao.getExpertEvents(token);
        List<ResponseEventDto> responseEventDtos = conversionListResultFactory.eventsToResponseEventDtos(events);
        return responseEventDtos;
    }

    @Override
    public List<ResponseBidDto> getExpertBids(String token) {
        verification.verifyTokenExistence(token);
        List<Bid> bids = bidDao.getExpertBids(token);
        List<ResponseBidDto> responseBidDtos = conversionListResultFactory.bidsToResponseBidDtos(bids);
        return responseBidDtos;
    }

    @Override
    public List<ResponseBidDto> getExpertEventBids(String token, long eventId) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertEventExistence(token, eventId);
        List<Bid> bids = bidDao.getExpertEventBids(token, eventId);
        List<ResponseBidDto> responseBidDtos = conversionListResultFactory.bidsToResponseBidDtos(bids);
        return responseBidDtos;
    }

    @Override
    public ResponseBidDto getExpertBid(String token, long bidId) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertBidExistence(token, bidId);
        Bid bid = bidDao.getBidById(bidId);
        ResponseBidDto responseBidDto = conversionResultFactory.bidToResponseBidDto(bid);
        return responseBidDto;
    }

    @Override
    public ResponseBidDto validate(String token, long bidId, ValidateDto validateDto) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertBidExistence(token, bidId);
        Bid bid = bidDao.getBidById(bidId);
        bid.setStatus(validateDto.getStatus());
        bid.setComment(validateDto.getComment());
        Bid updatedBid = bidDao.updateBid(bid);
        ResponseBidDto responseBidDto = conversionResultFactory.bidToResponseBidDto(updatedBid);
        return responseBidDto;
    }

    @Override
    public ResponseBanDto banUser(String token, long bidId, String comment) {
        verification.verifyTokenExistence(token);
        verification.verifyExpertBidExistence(token, bidId);
        Event event = eventDao.getEventByBidId(bidId);
        User expert = userDao.getUserByToken(token);
        User user = userDao.getUserByBidId(bidId);
        Ban ban = Ban.builder()
                .event(event)
                .expert(expert)
                .user(user)
                .comment(comment)
                .build();
        Ban addedBan = userDao.banUser(ban);
        ResponseBanDto responseBanDto = conversionResultFactory.banToResponseBanDto(addedBan);
        bidDao.deleteBid(bidId);
        return responseBanDto;
    }

    @Override
    public void unbanUser(String token, long userId) {
        verification.verifyTokenExistence(token);
        verification.verifyUserIdExistence(userId);
        userDao.unbanUser(token, userId);
    }

    @Override
    public List<ResponseBanDto> getBans(String token) {
        verification.verifyTokenExistence(token);
        List<Ban> bans = userDao.getBans(token);
        List<ResponseBanDto> responseBanDtos = conversionListResultFactory.bansToResponseBanDtos(bans);
        return responseBanDtos;
    }
}
