package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseEventDto;
import ru.itis.grant.model.Event;

public class EventToResponseEventDtoConverter {
    private static volatile EventToResponseEventDtoConverter instance;

    public static EventToResponseEventDtoConverter getInstance() {
        EventToResponseEventDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (EventToResponseEventDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new EventToResponseEventDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseEventDto convert(Event event) {
        return ResponseEventDto.builder()
                .id(event.getId())
                .owner(UserToResponseUserDtoConverter.getInstance().convert(event.getOwner()))
                .name(event.getName())
                .description(event.getDescription())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .siteUrl(event.getSiteUrl())
                .build();
    }
}