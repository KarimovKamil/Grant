package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;

import java.util.List;

public interface CreatorService {

    String login(AuthDto authDto);

    List<EventDto> getEvents();

    List<EventDto> getActiveEvents();

    EventDto getEvent(long eventId);

    PatternDto createPattern(long eventId, PatternDto patternDto);

    List<PatternDto> getPatterns(String token);

    PatternDto getPattern(String token, long patternId);

    PatternDto updatePattern(String token, PatternDto patternDto);

    boolean deletePattern(String token, long patternId);

}
