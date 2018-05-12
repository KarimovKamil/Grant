package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.*;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.*;
import ru.itis.grant.security.exception.IncorrectDataException;
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
    ConversionResultFactory conversionFactory;
    @Autowired
    ConversionListResultFactory conversionListFactory;
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
    @Autowired
    ElementValueDao elementValueDao;

    @Override
    public String login(AuthDto authDto) {
        verification.verifyEmailExistence(authDto.getEmail());
        User userFromDB = userDao.getUserByEmail(authDto.getEmail());
        if (hashGenerator.match(authDto.getPassword(), userFromDB.getHashPassword())) {
            String token = tokenGenerator.generateToken();
            userFromDB.setToken(token);
            userDao.updateUser(userFromDB);
            return token;
        } else {
            throw new IncorrectDataException("email and password", "Неверный email или пароль");
        }
    }

    @Override
    public String register(RequestUserDto userDto) {
        verification.verifyEmailUnique(userDto.getEmail());
        User user = conversionFactory.requestUserDtoToUser(
                tokenGenerator.generateToken(),
                hashGenerator.encode(userDto.getPassword()),
                userDto);
        user.setRole("USER");
        userDao.addUser(user);
        return user.getToken();
    }

    @Override
    public List<ResponseEventDto> getEvents() {
        List<Event> events = eventDao.getEvents();
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEvents() {
        List<Event> events = eventDao.getActiveEvents(new Date(System.currentTimeMillis()));
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public List<ResponseEventDto> getActiveEventsWithPattern() {
        List<Event> events = eventDao.getActiveEventsWithPattern(new Date(System.currentTimeMillis()));
        List<ResponseEventDto> eventDtoList = conversionListFactory.eventsToResponseEventDtos(events);
        return eventDtoList;
    }

    @Override
    public ResponseEventDto getEvent(long eventId) {
        verification.verifyEventExistenceById(eventId);
        Event event = eventDao.getEvent(eventId);
        ResponseEventDto responseEventDto = conversionFactory.eventToResponseEventDto(event);
        return responseEventDto;
    }

    @Override
    public ResponsePatternDto getEventPattern(long eventId) {
        verification.verifyEventExistenceById(eventId);
        verification.verifyEventPatternExistence(eventId);
        Pattern pattern = patternDao.getEventPattern(eventId);
        ResponsePatternDto responsePatternDto = conversionFactory.patternToResponsePatternDto(pattern);
        return responsePatternDto;
    }

    @Override
    public ResponseBidDto createBid(String token, RequestBidDto requestBidDto) {
        Date currentDate = new Date(System.currentTimeMillis());
        verification.verifyTokenExistence(token);
        verification.verifyPatternExistence(requestBidDto.getPatternId());
        verification.verifyPatternTimeLimit(requestBidDto.getPatternId(), currentDate);
        Pattern pattern = patternDao.getPattern(requestBidDto.getPatternId());
        verification.verifyBidDto(requestBidDto, pattern);
        Bid bid = conversionFactory.requestBidDtoToBid(requestBidDto);
        User user = userDao.getUserByToken(token);
        bid.setBidDate(currentDate);
        bid.setStatus("ACTIVE");
        bid.setUser(user);
        bid.setPattern(pattern);
        bidDao.addBid(bid);
        Bid bidFromDB = bidDao.getBidById(bid.getId());
        ResponseBidDto responseBidDto = conversionFactory.bidToResponseBidDto(bidFromDB);
        return responseBidDto;
    }

    @Override
    public List<ResponseBidDto> getUserBids(String token) {
        verification.verifyTokenExistence(token);
        List<Bid> bids = bidDao.getUserBids(token);
        List<ResponseBidDto> bidDtoList = conversionListFactory.bidsToResponseBidDtos(bids);
        return bidDtoList;
    }

    @Override
    public ResponseBidDto getBid(String token, long bidId) {
        verification.verifyTokenExistence(token);
        verification.verifyUserBidExistence(token, bidId);
        Bid bid = bidDao.getBidById(bidId);
        ResponseBidDto responseBidDto = conversionFactory.bidToResponseBidDto(bid);
        return responseBidDto;
    }

    @Override
    public ResponseBidDto updateBid(long id, String token, RequestBidDto requestBidDto) {
        Date currentDate = new Date(System.currentTimeMillis());
        verification.verifyTokenExistence(token);
        verification.verifyUserBidExistenceById(token, id);
        Bid bid = bidDao.getBidById(id);
        verification.verifyPatternTimeLimit(bid.getPattern().getId(), currentDate);
        verification.verifyBidDto(requestBidDto, bid.getPattern());
        bid.setBidDate(currentDate);
        bid.setStatus("ACTIVE");
        //TODO: конвертер из requestValues в values
//        bid.setValueList();
        bidDao.updateBid(bid);
        ResponseBidDto responseBidDto = conversionFactory.bidToResponseBidDto(bid);
        return responseBidDto;
    }

    @Override
    public boolean deleteBid(String token, long bidId) {
        verification.verifyTokenExistence(token);
        verification.verifyUserBidExistenceById(token, bidId);
        bidDao.deleteBid(bidId);
        return true;
    }
}
