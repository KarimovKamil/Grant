package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestBidDto;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Bid;
import ru.itis.grant.model.ElementValue;
import ru.itis.grant.model.Pattern;

import java.util.ArrayList;
import java.util.List;

public class RequestBidDtoToBidConverter {
    private static volatile RequestBidDtoToBidConverter instance;

    public static RequestBidDtoToBidConverter getInstance() {
        RequestBidDtoToBidConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestBidDtoToBidConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestBidDtoToBidConverter();
                }
            }
        }
        return localInstance;
    }

    public Bid convert(RequestBidDto requestBidDto) {
        Pattern pattern = Pattern.builder()
                .id(requestBidDto.getPatternId())
                .build();
        List<ElementValue> elementValueList = new ArrayList<>();
        for (RequestElementValueDto requestElementValueDto : requestBidDto.getValues()) {
            elementValueList.add(RequestElementValueDtoToElementValueConverter.getInstance()
                    .convert(requestElementValueDto));
        }
        return Bid.builder()
                .pattern(pattern)
                .valueList(elementValueList)
                .build();
    }
}