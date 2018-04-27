package ru.itis.grant.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

@Service
public class ExpertServiceImpl implements ExpertService {
    @Override
    public String login(AuthDto authDto) {
        return null;
    }

    @Override
    public List<EventDto> getEvents() {
        return null;
    }

    @Override
    public List<EventDto> getActiveEvents() {
        return null;
    }

    @Override
    public EventDto getEvent(long eventId) {
        return null;
    }

    @Override
    public List<PatternDto> getEventsPatterns(long eventId) {
        return null;
    }

    @Override
    public PatternDto getPattern(long eventId, long patternId) {
        return null;
    }

    @Override
    public boolean validatePattern(long eventId, long patternId, ValidateDto validateDto) {
        return false;
    }
}
