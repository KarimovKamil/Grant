package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestElementDto;
import ru.itis.grant.model.Element;

public class RequestElementDtoToElementConverter {
    private static volatile RequestElementDtoToElementConverter instance;

    public static RequestElementDtoToElementConverter getInstance() {
        RequestElementDtoToElementConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestElementDtoToElementConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestElementDtoToElementConverter();
                }
            }
        }
        return localInstance;
    }

    public Element convert(RequestElementDto requestElementDto) {
        return Element.builder()
                .type(requestElementDto.getType())
                .description(requestElementDto.getDescription())
                .name(requestElementDto.getName())
                .layoutX(requestElementDto.getLayoutX())
                .layoutY(requestElementDto.getLayoutY())
                .selectableValue(requestElementDto.getSelectableValue())
                .build();
    }
}