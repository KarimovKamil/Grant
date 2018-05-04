package ru.itis.grant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.Pattern;
import ru.itis.grant.service.interfaces.OrganizerService;

import java.util.List;

@Transactional
@Service
public class OrganizerServiceImpl implements OrganizerService {
    @Override
    public String login(AuthDto authDto) {
        return null;
    }

    @Override
    public List<ResponseEventDto> getUserEvents(String token) {
        return null;
    }

    @Override
    public List<ResponseEventDto> getActiveEvents() {
        return null;
    }

    @Override
    public ResponseEventDto getEvent(long eventId) {
        return null;
    }

    @Override
    public ResponsePatternDto createPattern(RequestPatternDto patternDto) {
        return null;
    }

    @Override
    public List<ResponsePatternDto> getPatterns(String token) {
        return null;
    }

    @Override
    public ResponsePatternDto getPattern(String token, long patternId) {
        return null;
    }

    @Override
    public ResponsePatternDto updatePattern(String token, RequestPatternDto pattern) {
        return null;
    }

    @Override
    public boolean deletePattern(String token, long patternId) {
        return false;
    }
}
