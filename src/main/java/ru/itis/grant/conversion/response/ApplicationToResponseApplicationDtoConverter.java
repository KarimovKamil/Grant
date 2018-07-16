package ru.itis.grant.conversion.response;

import ru.itis.grant.dto.response.ResponseApplicationDto;
import ru.itis.grant.dto.response.ResponseElementValueDto;
import ru.itis.grant.model.Application;
import ru.itis.grant.model.ElementValue;

import java.util.ArrayList;
import java.util.List;

public class ApplicationToResponseApplicationDtoConverter {
    private static volatile ApplicationToResponseApplicationDtoConverter instance;

    public static ApplicationToResponseApplicationDtoConverter getInstance() {
        ApplicationToResponseApplicationDtoConverter localInstance = instance;
        if (localInstance == null) {
            synchronized (ApplicationToResponseApplicationDtoConverter.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApplicationToResponseApplicationDtoConverter();
                }
            }
        }
        return localInstance;
    }

    public ResponseApplicationDto convert(Application application) {
        List<ResponseElementValueDto> responseElementValueDtoList = new ArrayList<>();
        for (ElementValue elementValue : application.getValueList()) {
            responseElementValueDtoList.add(ElementValueToResponseElementValueDtoConverter.getInstance()
                    .convert(elementValue, elementValue.getElement()));
        }
        return ResponseApplicationDto.builder()
                .id(application.getId())
                .applicationDate(application.getApplicationDate())
                .status(application.getStatus())
                .values(responseElementValueDtoList)
                .comment(application.getComment())
                .build();
    }
}