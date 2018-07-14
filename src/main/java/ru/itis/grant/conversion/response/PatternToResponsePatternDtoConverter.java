package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseElementDto;
import ru.itis.grant.dto.response.ResponsePatternDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;

import java.util.ArrayList;
import java.util.List;

public class PatternToResponsePatternDtoConverter {
    private static volatile PatternToResponsePatternDtoConverter instance;

    public static PatternToResponsePatternDtoConverter getInstance() {
        PatternToResponsePatternDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (PatternToResponsePatternDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new PatternToResponsePatternDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponsePatternDto convert(Pattern pattern) {
        List<ResponseElementDto> responseElementDtoList = new ArrayList<>();
        for (Element element : pattern.getElements()) {
            responseElementDtoList.add(ElementToResponseElementDtoConverter.getInstance().convert(element));
        }
        return ResponsePatternDto.builder()
                .id(pattern.getId())
                .applicationName(pattern.getApplicationName())
                .startDate(pattern.getStartDate())
                .endDate(pattern.getEndDate())
                .event(EventToResponseEventDtoConverter.getInstance().convert(pattern.getEvent()))
                .elements(responseElementDtoList)
                .build();
    }
}