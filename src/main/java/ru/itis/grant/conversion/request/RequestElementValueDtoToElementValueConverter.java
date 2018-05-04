package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.ElementValue;

public class RequestElementValueDtoToElementValueConverter {
    private static volatile RequestElementValueDtoToElementValueConverter instance;

    public static RequestElementValueDtoToElementValueConverter getInstance() {
        RequestElementValueDtoToElementValueConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestElementValueDtoToElementValueConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestElementValueDtoToElementValueConverter();
                }
            }
        }
        return localInstance;
    }

    public ElementValue convert(RequestElementValueDto requestElementValueDto) {
        Element element = Element.builder()
                .id(requestElementValueDto.getElementId())
                .build();
        return ElementValue.builder()
                .filledValue(requestElementValueDto.getFilledValue())
                .element(element)
                .build();
    }
}