package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.dto.ValidateDto;

import java.util.List;

public interface ExpertService {

    String login(AuthDto authDto);

    List<EventDto> getEvents();

    List<EventDto> getActiveEvents();

    EventDto getEvent(long eventId);

    List<PatternDto> getEventsPatterns(long eventId);

    PatternDto getPattern(long eventId, long patternId);

    boolean validatePattern(long eventId, long patternId, ValidateDto validateDto);

}
