package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.*;
import ru.itis.grant.model.Event;

import java.util.Date;
import java.util.List;

public interface UserService {

    String login(AuthDto authDto);

    String register(AuthDto authDto);

    List<EventDto> getEvents();

    List<EventDto> getActiveEvents();

    List<EventDto> getActiveEventsWithPattern();

    EventDto getEvent(long eventId);

    PatternDto getEventPattern(long eventId);

    BidDto createBid(String token, long patternId, BidDto bidDto);

    List<BidDto> getUserBids(String token);

    BidDto getBid(String token, long bidId);

    BidDto updateBid(String token, BidDto bidDto);

    boolean deleteBid(String token, long bidId);
}
