package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;

import java.util.List;

public interface OrganizerService {

    ResponseEventDto createEvent(RequestEventDto eventDto, String token);

    ResponsePatternDto createPattern(RequestPatternDto patternDto, String token);

    List<ResponseEventDto> getOrganizerEvents(String token);

    ResponseEventDto updateEvent(RequestEventDto eventDto, long id, String token);

    void deleteEvent(long id, String token);

    void addExpertToEvent(long expertId, long eventId, String token);

    void deleteExpertFromEvent(long expertId, long eventId, String token);
}
