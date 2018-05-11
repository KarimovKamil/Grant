package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;

import java.util.List;

public interface ExpertService {

    List<ResponseEventDto> getExpertEvents(String token);

    List<ResponseBidDto> getExpertBids(String token);

    List<ResponseBidDto> getExpertEventBids(String token, long eventId);

    ResponseBidDto getExpertBid(String token, long bidId);

    ResponseBidDto validate(String token, long bidId, ValidateDto validateDto);

    ResponseBidDto banUser(String token, long bidId);

    ResponseBidDto unbanUser(String token, long bidId);
}
