package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseBidDto;
import ru.itis.grant.dto.response.ResponseElementValueDto;
import ru.itis.grant.model.Bid;
import ru.itis.grant.model.ElementValue;

import java.util.ArrayList;
import java.util.List;

public class BidToResponseBidDtoConverter {
    private static volatile BidToResponseBidDtoConverter instance;

    public static BidToResponseBidDtoConverter getInstance() {
        BidToResponseBidDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (BidToResponseBidDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BidToResponseBidDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseBidDto convert(Bid bid) {
        List<ResponseElementValueDto> responseElementValueDtoList = new ArrayList<>();
        for (ElementValue elementValue : bid.getValueList()) {
            responseElementValueDtoList.add(ElementValueToResponseElementValueDtoConverter.getInstance()
                    .convert(elementValue));
        }
        return ResponseBidDto.builder()
                .id(bid.getId())
                .user(UserToResponseUserDtoConverter.getInstance().convert(bid.getUser()))
                .pattern(PatternToResponsePatternDtoConverter.getInstance().convert(bid.getPattern()))
                .bidDate(bid.getBidDate())
                .status(bid.getStatus())
                .values(responseElementValueDtoList)
                .build();
    }
}