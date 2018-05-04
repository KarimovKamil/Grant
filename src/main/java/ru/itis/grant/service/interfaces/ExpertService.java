package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;

import java.util.List;

public interface ExpertService {

    String login(AuthDto authDto);

    List<ResponseEventDto> getEvents();

    List<ResponseEventDto> getActiveEvents();

    ResponseEventDto getEvent(long eventId);

    List<ResponsePatternDto> getEventsPatterns(long eventId);

    ResponsePatternDto getPattern(long eventId, long patternId);

    boolean validatePattern(long eventId, long patternId, ValidateDto validateDto);
}
