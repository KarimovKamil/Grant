package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Application;
import ru.itis.grant.model.ElementValue;
import ru.itis.grant.model.Pattern;

import java.util.ArrayList;
import java.util.List;

public class RequestApplicationDtoToApplicationConverter {
    private static volatile RequestApplicationDtoToApplicationConverter instance;

    public static RequestApplicationDtoToApplicationConverter getInstance() {
        RequestApplicationDtoToApplicationConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestApplicationDtoToApplicationConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestApplicationDtoToApplicationConverter();
                }
            }
        }
        return localInstance;
    }

    public Application convert(RequestApplicationDto requestApplicationDto) {
        Pattern pattern = Pattern.builder()
                .id(requestApplicationDto.getPatternId())
                .build();
        List<ElementValue> elementValueList = new ArrayList<>();
        for (RequestElementValueDto requestElementValueDto : requestApplicationDto.getValues()) {
            elementValueList.add(RequestElementValueDtoToElementValueConverter.getInstance()
                    .convert(requestElementValueDto));
        }
        return Application.builder()
                .pattern(pattern)
                .valueList(elementValueList)
                .build();
    }
}