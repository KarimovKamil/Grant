package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestApplicationDto;
import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.Pattern;

public class ApplicationDtoValidator {
    private static volatile ApplicationDtoValidator instance;

    public static ApplicationDtoValidator getInstance() {
        ApplicationDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (ApplicationDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ApplicationDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public boolean verify(RequestApplicationDto applicationDto, Pattern pattern) {
        next:
        for (Element element : pattern.getElements()) {
            for (RequestElementValueDto value : applicationDto.getValues()) {
                if (value.getElementId() == element.getId()) {
                    if (!ElementValueDtoValidator.getInstance().verify(value, element)) {
                        return false;
                    }
                    continue next;
                }
            }
            if (element.isRequired()) {
                return false;
            }
        }
        return true;
    }
}
