package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestUserDto;
import ru.itis.grant.dto.request.UserUpdateDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.dto.response.ResponseUserDto;

import java.util.List;

public interface UserService {

    String login(AuthDto authDto);

    String register(RequestUserDto authDto);

    ResponseUserDto userInfo(String token);

    ResponseUserDto updateUserInfo(String token, UserUpdateDto userUpdateDto);

    List<ResponseEventDto> getEvents();

    List<ResponseEventDto> getActiveEvents();

    List<ResponseEventDto> getActiveEventsWithPattern();

    ResponseEventDto getEvent(long eventId);

    ResponsePatternDto getEventPattern(long eventId);

    ResponseBidDto createBid(String token, RequestBidDto requestBidDto);

    List<ResponseBidDto> getUserBids(String token);

    ResponseBidDto getBid(String token, long bidId);

    ResponseBidDto updateBid(long id, String token, RequestBidDto requestBidDto);

    boolean deleteBid(String token, long bidId);
}
