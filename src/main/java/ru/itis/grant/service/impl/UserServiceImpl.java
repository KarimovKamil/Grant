package ru.itis.grant.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.grant.dto.AuthDto;
import ru.itis.grant.dto.BidDto;
import ru.itis.grant.dto.EventDto;
import ru.itis.grant.dto.PatternDto;
import ru.itis.grant.service.interfaces.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public String login(AuthDto authDto) {
        return null;
    }

    @Override
    public String register(AuthDto authDto) {
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
    public BidDto createBid(long eventId, long patternId, BidDto bidDto) {
        return null;
    }

    @Override
    public List<BidDto> getBids(String token) {
        return null;
    }

    @Override
    public BidDto getBid(String token, long bidId) {
        return null;
    }

    @Override
    public BidDto updateBid(String token, BidDto bidDto) {
        return null;
    }

    @Override
    public boolean deleteBid(String token, long bidId) {
        return false;
    }
}
