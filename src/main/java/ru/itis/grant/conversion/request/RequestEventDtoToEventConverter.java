package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestEventDto;
import ru.itis.grant.model.Event;

public class RequestEventDtoToEventConverter {
    private static volatile RequestEventDtoToEventConverter instance;

    public static RequestEventDtoToEventConverter getInstance() {
        RequestEventDtoToEventConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestEventDtoToEventConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestEventDtoToEventConverter();
                }
            }
        }
        return localInstance;
    }

    public Event convert(RequestEventDto requestEventDto) {
        return Event.builder()
                .description(requestEventDto.getDescription())
                .name(requestEventDto.getName())
                .siteUrl(requestEventDto.getSiteUrl())
                .startDate(requestEventDto.getStartDate())
                .endDate(requestEventDto.getEndDate())
                .build();
    }
}