package ru.itis.grant.validation.dto;

import ru.itis.grant.dto.request.RequestElementValueDto;
import ru.itis.grant.model.Element;
import ru.itis.grant.model.SelectableElement;

public class ElementValueDtoValidator {
    private static volatile ElementValueDtoValidator instance;

    public static ElementValueDtoValidator getInstance() {
        ElementValueDtoValidator localInstance = instance;
        if (localInstance == null) {
            synchronized (ElementValueDtoValidator.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new ElementValueDtoValidator();
                }
            }
        }
        return localInstance;
    }

    public boolean verify(RequestElementValueDto elementValueDto, Element element) {
        //TODO
        return false;
    }
}
