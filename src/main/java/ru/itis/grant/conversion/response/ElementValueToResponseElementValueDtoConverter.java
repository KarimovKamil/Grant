package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseElementValueDto;
import ru.itis.grant.model.ElementValue;

public class ElementValueToResponseElementValueDtoConverter {
    private static volatile ElementValueToResponseElementValueDtoConverter instance;

    public static ElementValueToResponseElementValueDtoConverter getInstance() {
        ElementValueToResponseElementValueDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (ElementValueToResponseElementValueDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ElementValueToResponseElementValueDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseElementValueDto convert(ElementValue elementValue) {
        return ResponseElementValueDto.builder()
                .id(elementValue.getId())
                .element(ElementToResponseElementDtoConverter.getInstance().convert(elementValue.getElement()))
                .filledValue(elementValue.getFilledValue())
                .build();
    }
}