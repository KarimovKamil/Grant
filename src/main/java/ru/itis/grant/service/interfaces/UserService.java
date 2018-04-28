package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.*;

import java.util.List;

public interface UserService {

    String login(AuthDto authDto);

    String register(AuthDto authDto);

    List<EventDto> getEvents();

    List<EventDto> getActiveEvents();

    EventDto getEvent(long eventId);

    List<PatternDto> getEventsPatterns(long eventId);

    PatternDto getPattern(long eventId, long patternId);

    BidDto createBid(long eventId, long patternId, BidDto bidDto);

    List<BidDto> getBids(String token);

    BidDto getBid(String token, long bidId);

    BidDto updateBid(String token, BidDto bidDto);

    boolean deleteBid(String token, long bidId);
}