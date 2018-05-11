package ru.itis.grant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.grant.conversion.ConversionListResultFactory;
import ru.itis.grant.conversion.ConversionResultFactory;
import ru.itis.grant.dao.interfaces.BidDao;
import ru.itis.grant.dao.interfaces.EventDao;
import ru.itis.grant.dto.ValidateDto;
import ru.itis.grant.dto.request.AuthDto;
import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.Bid;
import ru.itis.grant.model.Event;
import ru.itis.grant.service.interfaces.ExpertService;

import java.util.List;

@Transactional
@Service
public class ExpertServiceImpl implements ExpertService {

    @Autowired
    BidDao bidDao;
    @Autowired
    EventDao eventDao;
    @Autowired
    ConversionResultFactory conversionResultFactory;
    @Autowired
    ConversionListResultFactory conversionListResultFactory;

    @Override
    public List<ResponseEventDto> getExpertEvents(String token) {
        List<Event> events = eventDao.getExpertEvents(token);
        List<ResponseEventDto> responseEventDtos = conversionListResultFactory.eventsToResponseEventDtos(events);
        return responseEventDtos;
    }

    @Override
    public List<ResponseBidDto> getExpertBids(String token) {
        List<Bid> bids = bidDao.getExpertBids(token);
        List<ResponseBidDto> responseBidDtos = conversionListResultFactory.bidsToResponseBidDtos(bids);
        return responseBidDtos;
    }

    @Override
    public ResponseBidDto getExpertBid(String token, long bidId) {
        Bid bid = bidDao.getExpertBid(token, bidId);
        ResponseBidDto responseBidDto = conversionResultFactory.bidToResponseBidDto(bid);
        return responseBidDto;
    }

    @Override
    public ResponseBidDto validate(String token, long bidId, ValidateDto validateDto) {
        Bid bid = bidDao.getBidById(bidId);
        bid.setStatus(validateDto.getStatus());
        bid.setComment(validateDto.getComment());
        Bid updatedBid = bidDao.updateBid(bid);
        ResponseBidDto responseBidDto = conversionResultFactory.bidToResponseBidDto(updatedBid);
        return responseBidDto;
    }

    @Override
    public ResponseBidDto banUser(String token, long bidId) {
        return null;
    }

    @Override
    public ResponseBidDto unbanUser(String token, long bidId) {
        return null;
    }
}
