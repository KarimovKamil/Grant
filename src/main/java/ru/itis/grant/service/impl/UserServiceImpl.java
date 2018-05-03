package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.BidDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.service.interfaces.UserService;
import ru.itis.grant.service.utils.generators.HashGenerator;
import ru.itis.grant.service.utils.generators.TokenGenerator;
import ru.itis.grant.validation.verification.Verification;

import java.util.Date;
import java.util.List;

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
    public String register(AuthDto authDto) {
        return null;
    }

    @Override
    public List<EventDto> getEvents() {
        List<Event> events = eventDao.getEvents();
        return null;
    }

    @Override
    public List<EventDto> getActiveEvents() {
        List<Event> events = eventDao.getActiveEvents(new Date(System.currentTimeMillis()));
        return null;
    }

    @Override
    public EventDto getEvent(long eventId) {
        Event event = eventDao.getEvent(eventId);
        return null;
    }

    @Override
    public PatternDto getPattern(long eventId) {
        Pattern pattern = patternDao.getEventPattern(eventId);
        return null;
    }

    @Override
    public BidDto createBid(String token, long patternId, BidDto bidDto) {
        return null;
    }

    @Override
    public List<BidDto> getBids(String token) {
        return null;
    }

    @Override
    public BidDto getBid(String token, long bidId) {
        return null;
    }

    @Override
    public BidDto updateBid(String token, BidDto bidDto) {
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
