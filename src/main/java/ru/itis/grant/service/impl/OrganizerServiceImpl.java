package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.*;
import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;
import ru.itis.grant.model.Element;
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
    ApplicationDao applicationDao;
    @Autowired
    ElementDao elementDao;

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
    public ResponsePatternDto getPatternByEventId(String token, long eventId) {
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyEventPatternExistence(eventId);
        Pattern pattern = patternDao.getEventPattern(eventId);
        return conversionFactory.patternToResponsePatternDto(pattern);
    }

    @Override
    public ResponsePatternDto createPattern(long eventId, RequestPatternDto patternDto, String token) {
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyPatternAddingCase(eventId);
        verification.verifyPatternDto(patternDto);
        Pattern pattern = conversionFactory.requestPatternDtoToPattern(patternDto);
        pattern.setEvent(eventDao.getEvent(eventId));
        patternDao.addPattern(pattern);
        for (Element element : pattern.getElements()) {
            element.setPattern(pattern);
            elementDao.addElement(element);
        }
        return conversionFactory.patternToResponsePatternDto(pattern);
    }

    @Override
    public ResponseEventDto deletePatternByEventId(String token, long eventId) {
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyEventPatternExistence(eventId);
        Event event = eventDao.getEvent(eventId);
        patternDao.deletePattern(event.getPattern());
        event.setPattern(null);
        return conversionFactory.eventToResponseEventDto(event);
    }

    @Override
    public List<ResponseEventDto> getOrganizerEvents(String token, int from, int count) {
        verification.verifyTokenExistence(token);
        verification.verifyFromCount(from, count);
        List<Event> events = eventDao.getOrganizerEvents(token, from, count);
        return conversionListFactory.eventsToResponseEventDtos(events);
    }

    @Override
    public ResponseEventDto updateEvent(RequestEventDto eventDto, long id, String token) {
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
    public ResponseEventDto getEvent(String token, long id) {
        verification.verifyOrganizerEventExistence(id, token);
        Event event = eventDao.getEvent(id);
        return conversionFactory.eventToResponseEventDto(event);
    }

    @Override
    public void deleteEvent(long id, String token) {
        verification.verifyOrganizerEventExistence(id, token);
        eventDao.deleteEvent(eventDao.getEvent(id));
    }

    @Override
    public void addExpertToEvent(long expertId, long eventId, String token) {
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyEventExpertAddingCase(eventId, expertId);
        verification.verifyUserIdExistence(expertId);
        eventDao.addExpertToEvent(eventId, expertId);
    }

    @Override
    public void deleteExpertFromEvent(long expertId, long eventId, String token) {
        verification.verifyOrganizerEventExistence(eventId, token);
        verification.verifyUserIdExistence(expertId);
        verification.verifyEventExpertExistence(eventId, expertId);
        eventDao.deleteExpertFromEvent(eventId, expertId);
    }

    @Override
    public List<ResponseUserDto> getUsers(int from, int count) {
        verification.verifyFromCount(from, count);
        return conversionListFactory.usersToResponseUserDtos(userDao.getAllUsersFromCount(from, count));
    }
}
