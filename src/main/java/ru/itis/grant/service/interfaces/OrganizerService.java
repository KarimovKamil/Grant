package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;

import java.util.List;

public interface OrganizerService {

    String login(AuthDto authDto);

    List<ResponseEventDto> getUserEvents(String token);

    List<ResponseEventDto> getActiveEvents();

    ResponseEventDto getEvent(long eventId);

    ResponsePatternDto createPattern(RequestPatternDto patternDto);

    List<ResponsePatternDto> getPatterns(String token);

    ResponsePatternDto getPattern(String token, long patternId);

    ResponsePatternDto updatePattern(String token, RequestPatternDto pattern);

    boolean deletePattern(String token, long patternId);
}
