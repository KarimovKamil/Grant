package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dao.interfaces.PatternDao;
import ru.itis.grant.dao.interfaces.UserDao;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.model.User;
import ru.itis.grant.service.interfaces.OrganizerService;
import ru.itis.grant.service.utils.generators.HashGenerator;
import ru.itis.grant.service.utils.generators.TokenGenerator;
import ru.itis.grant.validation.verification.Verification;

import java.util.List;

@Transactional
@Service
public class OrganizerServiceImpl implements OrganizerService {

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

    @Override
    public ResponseEventDto createEvent(RequestEventDto eventDto, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyEventDto(eventDto);
        Event event = conversionFactory.requestEventDtoToEvent(eventDto);
        User user = userDao.getUserByToken(token);
        event.setOwner(user);
        eventDao.addEvent(event);
        return conversionFactory.eventToResponseEventDto(event);
    }

    @Override
    public ResponsePatternDto createPattern(RequestPatternDto patternDto, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyPatternDto(patternDto);
        verification.verifyOrganizerEventExistence(patternDto.getEventId(), token);
        Pattern pattern = conversionFactory.requestPatternDtoToPattern(patternDto);
        pattern.setEvent(eventDao.getEvent(patternDto.getEventId()));
        patternDao.addPattern(pattern);
        return conversionFactory.patternToResponsePatternDto(pattern);
    }

    @Override
    public List<ResponseEventDto> getOrganizerEvents(String token) {
        verification.verifyTokenExistence(token);
        List<Event> events = eventDao.getOrganizerEvents(token);
        return conversionListFactory.eventsToResponseEventDtos(events);
    }

    @Override
    public ResponseEventDto updateEvent(RequestEventDto eventDto, long id, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyOrganizerEventExistence(id, token);
        verification.verifyEventDto(eventDto);
        Event event = eventDao.getEvent(id);
        event.setDescription(eventDto.getDescription());
        event.setName(eventDto.getName());
        event.setSiteUrl(eventDto.getSiteUrl());
        eventDao.updateEvent(event);
        return conversionFactory.eventToResponseEventDto(event);
    }

    @Override
    public void deleteEvent(long id, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyOrganizerEventExistence(id, token);
        eventDao.deleteEvent(id);
    }

    @Override
    public void addExpertToEvent(long expertId, long eventId, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyUserIdExistence(expertId);
        eventDao.addExpertToEvent(eventId, expertId);
    }

    @Override
    public void deleteExpertFromEvent(long expertId, long eventId, String token) {
        verification.verifyTokenExistence(token);
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyUserIdExistence(expertId);
        verification.verifyEventExpertExistence(eventId, expertId);
        eventDao.deleteEvent(eventId);
    }
}
