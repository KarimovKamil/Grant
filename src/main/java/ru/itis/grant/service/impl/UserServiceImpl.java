package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.Bid;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.service.interfaces.UserService;
import ru.itis.grant.service.utils.generators.HashGenerator;
import ru.itis.grant.service.utils.generators.TokenGenerator;
import ru.itis.grant.validation.verification.Verification;

import java.util.Date;
import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    Verification verification;
    @Autowired
    HashGenerator hashGenerator;
    @Autowired
    TokenGenerator tokenGenerator;
    @Autowired
    UserDao userDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    PatternDao patternDao;
    @Autowired
    BidDao bidDao;

    @Override
    public String login(AuthDto authDto) {
        return null;
    }

    @Override
    public String register(RequestUserDto authDto) {
        return null;
    }

    @Override
    public List<ResponseEventDto> getEvents() {
        List<Event> events = eventDao.getEvents();
        return null;
    }

    @Override
    public List<ResponseEventDto> getActiveEvents() {
        List<Event> events = eventDao.getActiveEvents(new Date(System.currentTimeMillis()));
        return null;
    }

    @Override
    public List<ResponseEventDto> getActiveEventsWithPattern() {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()));
        return null;
    }

    @Override
    public ResponseEventDto getEvent(long eventId) {
        verification.verifyEventExistenceById(eventId);
        Event event = eventDao.getEvent(eventId);
        return null;
    }

    @Override
    public ResponsePatternDto getEventPattern(long eventId) {
        verification.verifyEventExistenceById(eventId);
        verification.verifyEventPatternExistence(eventId);
        Pattern pattern = patternDao.getEventPattern(eventId);
        return null;
    }

    @Override
    public ResponseBidDto createBid(String token, RequestBidDto requestBidDto) {
        return null;
    }

    @Override
    public List<ResponseBidDto> getUserBids(String token) {
        verification.verifyTokenExistence(token);
        List<Bid> bids = bidDao.getUserBids(token);
        return null;
    }

    @Override
    public ResponseBidDto getBid(String token, long bidId) {
        verification.verifyTokenExistence(token);
        verification.verifyUserBidExistence(token, bidId);
        Bid bid = bidDao.getBidById(bidId);
        return null;
    }

    @Override
    public ResponseBidDto updateBid(String token, RequestBidDto requestBidDto) {
        return null;
    }

    @Override
    public boolean deleteBid(String token, long bidId) {
        verification.verifyTokenExistence(token);
        verification.verifyBidExistenceById(bidId);
        bidDao.deleteBid(bidId);
        return false;
    }
}
