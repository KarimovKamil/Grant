package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseElementDto;
import ru.itis.grant.model.Element;

public class ElementToResponseElementDtoConverter {
    private static volatile ElementToResponseElementDtoConverter instance;

    public static ElementToResponseElementDtoConverter getInstance() {
        ElementToResponseElementDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (ElementToResponseElementDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ElementToResponseElementDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseElementDto convert(Element element) {
        return ResponseElementDto.builder()
                .id(element.getId())
                .name(element.getName())
                .type(element.getType())
                .description(element.getDescription())
                .layoutX(element.getLayoutX())
                .layoutY(element.getLayoutY())
                .required(element.isRequired())
                .selectableValue(element.getSelectableValue())
                .build();
    }
}