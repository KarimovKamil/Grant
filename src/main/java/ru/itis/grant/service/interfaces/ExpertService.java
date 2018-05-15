package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBanDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;

import java.util.List;

public interface ExpertService {

    List<ResponseEventDto> getExpertEvents(String token);

    List<ResponseBidDto> getExpertBids(String token);

    List<ResponseBidDto> getExpertEventBids(String token, long eventId);

    ResponseBidDto getExpertBid(String token, long bidId);

    ResponseBidDto validate(String token, long bidId, ValidateDto validateDto);

    ResponseBanDto banUser(String token, long bidId, String comment);

    void unbanUser(String token, long userId);

    List<ResponseBanDto> getBans(String token);
}
