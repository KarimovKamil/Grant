package ru.itis.grant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

@Transactional
@Service
public class ExpertServiceImpl implements ExpertService {
    @Override
    public String login(AuthDto authDto) {
        return null;
    }

    @Override
    public List<ResponseEventDto> getEvents() {
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
    public List<ResponsePatternDto> getEventsPatterns(long eventId) {
        return null;
    }

    @Override
    public ResponsePatternDto getPattern(long eventId, long patternId) {
        return null;
    }

    @Override
    public boolean validatePattern(long eventId, long patternId, ValidateDto validateDto) {
        return false;
    }
}
