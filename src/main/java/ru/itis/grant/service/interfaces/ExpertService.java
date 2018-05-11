package ru.itis.grant.service.interfaces;

import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;

import java.util.List;

public interface ExpertService {

    List<ResponseEventDto> getExpertEvents(String token);

    List<ResponseBidDto> getExpertBids(String token);

    ResponseBidDto getExpertBid(String token, long bidId);

    ResponseBidDto validate(String token, long eventId, long bidId, ValidateDto validateDto);
}
