package ru.itis.grant.conversion.request;

import ru.itis.grant.dto.request.RequestElementDto;
import ru.itis.grant.dto.request.RequestPatternDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Event;
import ru.itis.grant.model.Pattern;

import java.util.ArrayList;
import java.util.List;

public class RequestPatternDtoToPatternConverter {
    private static volatile RequestPatternDtoToPatternConverter instance;

    public static RequestPatternDtoToPatternConverter getInstance() {
        RequestPatternDtoToPatternConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (RequestPatternDtoToPatternConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new RequestPatternDtoToPatternConverter();
                }
            }
        }
        return localInstance;
    }

    public Pattern convert(RequestPatternDto requestPatternDto) {
        List<Element> elementList = new ArrayList<>();
        for (RequestElementDto requestElementDto : requestPatternDto.getElements()) {
            elementList.add(RequestElementDtoToElementConverter.getInstance().convert(requestElementDto));
        }
        return Pattern.builder()
                .applicationName(requestPatternDto.getApplicationName())
                .elements(elementList)
                .build();
    }
}