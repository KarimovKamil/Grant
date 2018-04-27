package ru.itis.grant.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.service.interfaces.CreatorService;

import java.util.List;

@Service
public class CreatorServiceImpl implements CreatorService {
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
    public PatternDto createPattern(long eventId, PatternDto patternDto) {
        return null;
    }

    @Override
    public List<PatternDto> getPatterns(String token) {
        return null;
    }

    @Override
    public PatternDto getPattern(String token, long patternId) {
        return null;
    }

    @Override
    public PatternDto updatePattern(String token, PatternDto patternDto) {
        return null;
    }

    @Override
    public boolean deletePattern(String token, long patternId) {
        return false;
    }
}
