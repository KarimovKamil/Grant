package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;

import java.util.List;

public interface OrganizerService {

    ResponseEventDto createEvent(RequestEventDto eventDto, String token);

    ResponsePatternDto getPatternByEventId(String token, long eventId);

    ResponsePatternDto createPattern(long eventId, RequestPatternDto patternDto, String token);

    ResponseEventDto deletePatternByEventId(String token, long eventId);

    List<ResponseEventDto> getOrganizerEvents(String token, int from, int count);

    ResponseEventDto updateEvent(RequestEventDto eventDto, long id, String token);

    ResponseEventDto getEvent(String token, long id);

    void deleteEvent(long id, String token);

    void addExpertToEvent(long expertId, long eventId, String token);

    void deleteExpertFromEvent(long expertId, long eventId, String token);

    List<ResponseUserDto> getUsers(int from, int count);
}
